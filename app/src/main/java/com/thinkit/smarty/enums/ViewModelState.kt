package com.thinkit.smarty.enums
/**
 * view model states used when checking the state of an operation in view model from a view
 */
enum class ViewModelState {
    /**
     * default state
     */
    IDLE,
    /**
     * loading state
     */
    LOADING,
    /**
     * success state
     */
    SUCCESS,
    /**
     * error state
     */
    ERROR
}