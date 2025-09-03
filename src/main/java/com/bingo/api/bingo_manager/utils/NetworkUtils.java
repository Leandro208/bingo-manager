package com.bingo.api.bingo_manager.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {

	 /**
     * Retorna o nome da maquina em que o matodo a executado
     *
     * @return
     */
    public static String getLocalName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            return null;
        }
    }

}
