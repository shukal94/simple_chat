package com.context;

import com.context.manager.ContextManager;

public class ChatConstant {
    public static final String HOSTNAME = ContextManager.getInstance().getProperty("hostname");
    public static final String PORT = ContextManager.getInstance().getProperty("port");
}
