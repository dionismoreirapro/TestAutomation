package br.com.yampa.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Text;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class LoginPage {


    public LoginPage open() {
        Selenide.open("/?v=01.03.08#Login");
        return this;
    }

    public LoginPage with(String email, String pass) {
        $("input[name=email]").setValue(email);
        $("input[type=password]").setValue(pass);
        $(byText("Entrar")).click();
        return this;
    }

    public LoginPage recoveringPassword(String email, String pass){
        $(".rememberPasswordBT").click();
        $(".closeBTLookup").click();
        $("input[name=email]").setValue(email);
        $("input[name=rememberByCompayDoc]").setValue(pass);
        $(".rememberPasswordBTGO").click();
        return this;
    }


    public SelenideElement alerts() {
        return $(".messagesPanelItemError");
    }

    public SelenideElement menssageSucess(){
        return  $(".messageLB");

    }

    public void clearSession(){
        executeJavaScript("localStorage.clear();");
    }
}
