package br.com.yampa.Test;

import br.com.yampa.pages.LoginPage;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;


public class LoginTests {

    protected LoginPage login;

    @DataProvider(name = "recover-pass")
    public Object[][] recoverPassProvider() {
        return new Object[][]{
                {"dionis.rodrigo@gmail.com","","Uma nova senha foi enviada para seu e-mail." },
                {"", "90089625005","Uma nova senha foi enviada para seu e-mail." }
        };
    }

    @DataProvider(name = "Login-alerts")
    public Object[][] loginProvider() {
        return new Object[][]{
                {"dionis.rodrigo@gmail.com", "123", "E-mail e/ou senha inválidos"},
                {"Email@invalido.com", "432", "E-mail e/ou senha inválidos"},
                {"dionis.rodrigo@gmail.com", "", "O campo Senha deve ser preenchido"},
                {"", "3232", "O campo E-mail deve ser preenchido"}
        };
    }

    @BeforeMethod
    public void setupStart() {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://www.yampa.com.br";
        login = new LoginPage();
    }

    @Test
    public void loginSucess() {

        login
                .open()
                .with("dionis.rodrigo@gmail.com", "cfyipp")
                .menssageSucess().shouldHave(text("Login efetuado com sucesso! Você será direcionado para o sistema."));
    }



    @Test(dataProvider = "Login-alerts")
    public void LoginAlert(String email, String password, String expectAlert) {

        login
                .open()
                .with(email, password)
                .alerts().shouldHave(text(expectAlert));
    }
    @Test(dataProvider = "recover-pass")
    public void recoverPassword(String email, String cpfCnpj, String expectAlert){
        login
                .open()
                .recoveringPassword(email,cpfCnpj)
                .menssageSucess().shouldHave(text(expectAlert));

    }
    @AfterMethod
    public void cleanUp(){
         login.clearSession();
    }
}
