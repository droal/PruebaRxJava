package com.example.pruebarxjava;

import com.example.pruebarxjava.login.LoginActivityMVP;
import com.example.pruebarxjava.login.LoginActivityPresenter;
import com.example.pruebarxjava.login.UserPojo;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PresenterUnitTest {

    LoginActivityPresenter presenter;
    UserPojo user;

    //Instancias que serán simuladas por mockito
    LoginActivityMVP.Model mockedModel;
    LoginActivityMVP.View mockedView;

    @Before //Indica a JUnit que cada vez que ejecute una prueba, primero ejecute este método
    public void initialization(){

        //Mockito crea y configura los objetos que necesitamos
        mockedModel = mock(LoginActivityMVP.Model.class);
        mockedView = mock(LoginActivityMVP.View.class);

        //Creamos un objeto de prueba
        user = new UserPojo("Name Dummy", "LastName Dummy");

        //Decimos a mockito como burlar el llamado al método "getUser", para retornar el objeto que queremos
        //when(mockedModel.getUser()).thenReturn(user);

        //Tambien se podrian simular métodos de la vista
        //when(mockedView.getFirstName()).thenReturn("Name Dummy");
        //when(mockedView.getLastName()).thenReturn("LastName Dummy");

        //Configurar la implementación que se quiere probar
        presenter = new LoginActivityPresenter(mockedModel);
        presenter.setView(mockedView);
    }


    @Test
    //Comprobar si existe interacción del presentador con la vista
    public void noExistsInteractionWithView(){
        presenter.getCurrentUser();

        /**Verificar si la vista no cambia luego de recuperar el usuario
        * Esta prueba va a fallar debido a que el método: "getCurrentUser" siempre presenta interacción con la vista
        * ya sea mostrando un toast de usuario no disponible o visualizando los datos del usuario*/
        //verifyZeroInteractions(mockedView);

        /**Verificar que "View" llama una sola vez al método: "showUserNotAvailable"
         * en este caso si en "initialization()" se retorna un usuario la prueba falla, ya que se generan dos interacciones
         * con la vista en lugar de una y no se ejecuta el método: "showUserNotAvailable"*/
        //verify(mockedView, times(1)).showUserNotAvailable();
        /**En este caso la prueba NO falla*/
        //verify(mockedView, never()).showUserNotAvailable();
    }

    @Test
    public void loadUserFromTheRepoWhenValidUserIsPresent(){
        when(mockedModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //Validar la interacción conel modelo de datos
        verify(mockedModel, times(1)).getUser();

        //Validar interacciones con la vista
        verify(mockedView, times(1)).setFirstName("Name Dummy");
        verify(mockedView, times(1)).setLastName("LastName Dummy");
        verify(mockedView, never()).showUserNotAvailable();
    }


    @Test
    public void showErrorMessageWhenUserIsNull(){
        when(mockedModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //Validar la interacción conel modelo de datos
        verify(mockedModel, times(1)).getUser();

        //Validar interacciones con la vista
        verify(mockedView, never()).setFirstName("Name Dummy");
        verify(mockedView, never()).setLastName("LastName Dummy");
        verify(mockedView, times(1)).showUserNotAvailable();
    }

    @Test
    public void createErrorMessageIfAnyFieldIsEmpty(){
        //Primera prueba, campo first name vacío
        when(mockedView.getFirstName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView, times(1)).getFirstName();
        verify(mockedView, never()).getLastName();
        verify(mockedView, times(1)).showInputError();

        //Segunda prueba, campo firstname con valor y last name vacío
        when(mockedView.getFirstName()).thenReturn("FirstName Dummy");
        when(mockedView.getLastName()).thenReturn("");

        presenter.loginButtonClicked();

        verify(mockedView, times(2)).getFirstName();//El metodo ya fue llamado en la línea 105
        verify(mockedView, times(1)).getLastName();//En la primera prueba no se ejecuta este método ya que no pasa el test de firstname
        verify(mockedView, times(2)).showInputError();//El método se llamó en la primera prueba y ahora
    }

    @Test
    public void saveValidUser(){
        when(mockedView.getFirstName()).thenReturn("FirstName Dummy");
        when(mockedView.getLastName()).thenReturn("LastName Dummy");

        presenter.loginButtonClicked();

        //En el método: " loginButtonClicked()", se llaman dos veces la siguientes funciones, una en el if y la otra al crear el usuario
        verify(mockedView, times(2)).getFirstName();
        verify(mockedView, times(2)).getLastName();

        //Validar método de persistencia en el modelo
        verify(mockedModel, times(1)).createUser("FirstName Dummy", "LastName Dummy");

        //Validar visualización de mensaje de éxito
        verify(mockedView, times(1)).showUserSaved();
    }

}
