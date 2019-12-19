package com.example.android.guesstheword.screens.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class GameViewModel : ViewModel() {
    /** The current word*/
//    var word = ""
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    /** The current score*/
//    var score = 0
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    /** Event which triggers the end of the game*/
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    init {
        resetList()
        nextWord()
        _word.value = ""
        _score.value = 0
        Timber.i("GameViewModel created!")
    }

    /** The list of words - the front of the list is the next word to guess*/
    private lateinit var wordList: MutableList<String>

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /** Methods for buttons presses **/

    fun onSkip() {
        /**Aaron C: I think here don't need to check wordList is empty or not
         * it doesn't matter, if wordList is empty nextWord() will check
         * if here checked the last action will skip and won't change score*/
//        if (!wordList.isEmpty()) {
//            _score.value = (score.value)?.minus(1)
//        }
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        /**Aaron C: I think here don't need to check wordList is empty or not
         * it doesn't matter, if wordList is empty nextWord() will check
         * if here checked the last action will skip and won't change score*/
//        if (!wordList.isEmpty()) {
//            _score.value = (score.value)?.plus(1)
//        }

        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    /** Method for the game completed event **/
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    /** Method for the game completed event **/
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (wordList.isEmpty()) {
            onGameFinish()
        } else {
            /**Select and remove a _word from the list*/
            _word.value = wordList.removeAt(0)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Timber.i("GameViewModel destroyed!")
    }
}