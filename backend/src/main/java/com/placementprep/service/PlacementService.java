package com.placementprep.service;

import com.placementprep.entity.*; import com.placementprep.repository.*; import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service @Transactional
public class PlacementService {
    private static final double LOW_TOPIC_PROGRESS_THRESHOLD = 50.0;
    private static final double LOW_MOCK_SCORE_THRESHOLD = 60.0;

    private final ApplicationRepository applications; private final TopicRepository topics; private final StudySessionRepository sessions; private final MockTestRepository tests;
    public PlacementService(ApplicationRepository a,TopicRepository t,StudySessionRepository s,MockTestRepository m){applications=a;topics=t;sessions=s;tests=m;}
    public List<JobApplication> applications(){return applications.findAll();} public JobApplication application(long id){return applications.findById(id).orElseThrow(()->new NoSuchElementException("Application not found: "+id));}
    public JobApplication saveApplication(JobApplication item){return applications.save(item);} public void deleteApplication(long id){applications.delete(application(id));}
    public List<PreparationTopic> topics(String category){return category==null||category.isBlank()?topics.findAll():topics.findByCategoryIgnoreCase(category);}
    public PreparationTopic topic(long id){return topics.findById(id).orElseThrow(()->new NoSuchElementException("Topic not found: "+id));}
    public PreparationTopic saveTopic(PreparationTopic item){return topics.save(item);} public void deleteTopic(long id){topics.delete(topic(id));}
    public List<StudySession> sessions(){return sessions.findAll();} public StudySession saveSession(StudySession item){return sessions.save(item);}
    public void deleteSession(long id){if(!sessions.existsById(id))throw new NoSuchElementException("Study session not found: "+id);sessions.deleteById(id);}
    public List<MockTest> tests(){return tests.findAll();} public MockTest test(long id){return tests.findById(id).orElseThrow(()->new NoSuchElementException("Mock test not found: "+id));}
    public MockTest saveTest(MockTest item){return tests.save(item);} public void deleteTest(long id){if(!tests.existsById(id))throw new NoSuchElementException("Mock test not found: "+id);tests.deleteById(id);}
    public Map<String,Object> dashboard(){
        List<PreparationTopic> allTopics=topics.findAll();
        long totalTopics=allTopics.size(),completed=0,minutes=Optional.ofNullable(sessions.sumDurationMinutes()).orElse(0L);
        for(PreparationTopic topic:allTopics){if(topic.isCompleted())completed++;}
        double average=0,best=0;
        List<MockTest> allTests=tests.findAll(); for(MockTest test:allTests){average+=test.getPercentage();best=Math.max(best,test.getPercentage());} if(!allTests.isEmpty())average/=allTests.size();
        Map<String,Object> data=new LinkedHashMap<>(); data.put("totalApplications",applications.count()); data.put("appliedApplications",applications.countByStatus(ApplicationStatus.APPLIED));
        data.put("onlineAssessmentApplications",applications.countByStatus(ApplicationStatus.ONLINE_ASSESSMENT)); data.put("interviewApplications",applications.countByStatus(ApplicationStatus.INTERVIEW));
        data.put("selectedApplications",applications.countByStatus(ApplicationStatus.SELECTED)); data.put("rejectedApplications",applications.countByStatus(ApplicationStatus.REJECTED));
        data.put("totalTopics",totalTopics);data.put("completedTopics",completed);data.put("preparationPercentage",totalTopics==0?0:Math.round(completed*1000.0/totalTopics)/10.0);
        data.put("totalStudyHours",Math.round(minutes/60.0*100)/100.0);data.put("totalMockTests",allTests.size());data.put("averageMockScore",Math.round(average*10)/10.0);data.put("bestMockScore",Math.round(best*10)/10.0);
        data.put("weakAreas",findWeakAreas(allTopics,allTests));
        return data;
    }

    private List<Map<String,Object>> findWeakAreas(List<PreparationTopic> allTopics,List<MockTest> allTests){
        Map<String,int[]> topicCounts=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(PreparationTopic topic:allTopics){
            int[] counts=topicCounts.computeIfAbsent(topic.getCategory(),category->new int[2]);
            counts[0]++;
            if(topic.isCompleted())counts[1]++;
        }

        Map<String,double[]> mockScoreTotals=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(MockTest test:allTests){
            double[] totals=mockScoreTotals.computeIfAbsent(test.getSubject(),subject->new double[2]);
            totals[0]+=test.getPercentage();
            totals[1]++;
        }

        Map<String,List<String>> reasonsByArea=new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for(Map.Entry<String,int[]> entry:topicCounts.entrySet()){
            int total=entry.getValue()[0];
            int completed=entry.getValue()[1];
            double progressPercentage=completed*100.0/total;
            if(progressPercentage<LOW_TOPIC_PROGRESS_THRESHOLD){
                String reason="Topic progress is "+Math.round(progressPercentage)+"% ("+completed+" of "+total+" completed; below 50%).";
                addWeakAreaReason(reasonsByArea,entry.getKey(),reason);
            }
        }
        for(Map.Entry<String,double[]> entry:mockScoreTotals.entrySet()){
            double averageScore=entry.getValue()[0]/entry.getValue()[1];
            if(averageScore<LOW_MOCK_SCORE_THRESHOLD){
                String reason="Average mock-test score is "+Math.round(averageScore)+"% (below 60%).";
                addWeakAreaReason(reasonsByArea,entry.getKey(),reason);
            }
        }

        List<Map<String,Object>> weakAreas=new ArrayList<>();
        for(Map.Entry<String,List<String>> entry:reasonsByArea.entrySet()){
            Map<String,Object> area=new LinkedHashMap<>();
            area.put("area",entry.getKey());
            area.put("reasons",entry.getValue());
            weakAreas.add(area);
        }
        return weakAreas;
    }

    private void addWeakAreaReason(Map<String,List<String>> reasonsByArea,String area,String reason){
        reasonsByArea.computeIfAbsent(area,key->new ArrayList<>()).add(reason);
    }
}

