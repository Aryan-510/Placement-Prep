let timerStartedAt = null;
let pausedSeconds = 0;
let timerInterval = null;
let timerTopic = '';
let timerNotes = '';
let timerDate = '';

function elapsedTimerSeconds() {
    if (timerStartedAt === null) {
        return pausedSeconds;
    }
    return pausedSeconds + Math.floor((Date.now() - timerStartedAt) / 1000);
}

function displayTimer() {
    const seconds = elapsedTimerSeconds();
    const hours = Math.floor(seconds / 3600);
    const minutes = Math.floor((seconds % 3600) / 60);
    const remainingSeconds = seconds % 60;
    const twoDigits = value => String(value).padStart(2, '0');

    $('#timer-display').textContent = `${twoDigits(hours)}:${twoDigits(minutes)}:${twoDigits(remainingSeconds)}`;
    $('#timer-status').textContent = timerStartedAt === null
        ? (pausedSeconds > 0 ? 'Paused' : 'Ready')
        : 'Running';

    const hasTimer = timerStartedAt !== null || pausedSeconds > 0;
    $('#timer-start').disabled = hasTimer;
    $('#timer-pause').disabled = !hasTimer;
    $('#timer-pause').textContent = timerStartedAt === null ? 'Resume' : 'Pause';
    $('#timer-save').disabled = seconds < 30;
    $('#timer-discard').disabled = !hasTimer;
    $('#timer-topic').disabled = hasTimer;
    $('#timer-notes').disabled = hasTimer;
}

function startStudyTimer() {
    const topic = $('#timer-topic').value.trim();
    if (!topic) {
        $('#timer-message').textContent = 'Enter a topic before starting the timer.';
        $('#timer-topic').focus();
        return;
    }

    timerTopic = topic;
    timerNotes = $('#timer-notes').value.trim();
    timerDate = today();
    pausedSeconds = 0;
    timerStartedAt = Date.now();
    $('#timer-message').textContent = '';
    timerInterval = setInterval(displayTimer, 250);
    displayTimer();
}

function toggleStudyTimer() {
    if (timerStartedAt !== null) {
        pausedSeconds = elapsedTimerSeconds();
        timerStartedAt = null;
    } else if (pausedSeconds > 0) {
        timerStartedAt = Date.now();
    }
    displayTimer();
}

async function saveTimedSession() {
    const seconds = elapsedTimerSeconds();
    if (seconds < 30) {
        $('#timer-message').textContent = 'Run the timer for at least 30 seconds before saving.';
        return;
    }

    const durationMinutes = Math.max(1, Math.round(seconds / 60));
    const session = {
        date: timerDate,
        topic: timerTopic,
        durationMinutes,
        notes: timerNotes
    };

    try {
        await request('/study-sessions', {
            method: 'POST',
            body: JSON.stringify(session)
        });
        resetStudyTimer();
        notice(`Study session saved: ${durationMinutes} minute${durationMinutes === 1 ? '' : 's'}.`);
        refresh();
    } catch (error) {
        $('#timer-message').textContent = error.message;
    }
}

function discardStudyTimer() {
    if (elapsedTimerSeconds() > 0 && !confirm('Discard this timed session?')) {
        return;
    }
    resetStudyTimer();
}

function resetStudyTimer() {
    if (timerInterval !== null) {
        clearInterval(timerInterval);
    }
    timerStartedAt = null;
    pausedSeconds = 0;
    timerInterval = null;
    timerTopic = '';
    timerNotes = '';
    timerDate = '';
    $('#timer-topic').value = '';
    $('#timer-notes').value = '';
    $('#timer-message').textContent = '';
    displayTimer();
}

displayTimer();

