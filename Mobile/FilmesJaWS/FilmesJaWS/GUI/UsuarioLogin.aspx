<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="UsuarioLogin.aspx.cs" Inherits="FilmesJaWS.GUI.UsuarioLogin" %>
<%@ Import Namespace="FilmesJaWS.DAO" %>
<%
    string pEmail = "email";
    string pSenha = "senha";

    string email = "leandro";
    string senha = "tricolor";

    if (Request.Form[pEmail] != null && Request.Form[pSenha] != null)
    {
        email = Request.Form[pEmail].ToString();
        senha = Request.Form[pSenha].ToString();
    }

    DAOUsuario daoUsuario = new DAOUsuario();
    if (daoUsuario.Login(email, senha))
    {
        Response.Write(daoUsuario.IDUsuario);
    }
    else
    {
        Response.Write("0");
    }
  //  Response.Write(daoUsuario.getPerfilsConta(1));
   
     %>
