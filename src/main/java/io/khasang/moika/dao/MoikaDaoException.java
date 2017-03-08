package io.khasang.moika.dao;

public class MoikaDaoException extends Exception{

        public MoikaDaoException(String text)
        {
            super(text);
        }
        public MoikaDaoException(String text,Exception innerEx)
        {
            super(text,innerEx);
        }
}
