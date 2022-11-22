package com.masa.authentication;

import com.google.api.client.auth.oauth2.Credential;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author Andrea
 */
public interface IAuthenticationMethod {
    public Profile Login() throws Exception;
    public Credential authorize() throws Exception;
}
