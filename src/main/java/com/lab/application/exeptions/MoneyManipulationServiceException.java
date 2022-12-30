package com.lab.application.exeptions;

public class MoneyManipulationServiceException extends RuntimeException
{
    public static final String WRONG_MONEY_MANIPULATION_TYPE = "Wrong money manipulation type";
    public static final String REMOVE_FORBIDDEN = "Remove forbidden";

    public MoneyManipulationServiceException(String message)
    {
        super(message);
    }
}
