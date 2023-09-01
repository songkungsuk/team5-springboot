class AudioPlayer {
    constructor() { }

    play(el) {
        if (!el.ended) {
            this._stop(el);
        }
        el.play();
    }

    _stop(el) {
        el.pause();
        el.currentTime = 0.0;
    }
}
const player = new AudioPlayer();
const bgm = document.querySelector('#bgm');
const clearLine = document.querySelector('#clear_line');
const drop = document.querySelector('#drop');
const gameOver = document.querySelector('#game_over');


