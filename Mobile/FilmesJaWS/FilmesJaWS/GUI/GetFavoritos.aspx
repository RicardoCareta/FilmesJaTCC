<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="GetFavoritos.aspx.cs" Inherits="FilmesJaWS.GUI.GetFavoritos" %>
<%@ Import Namespace="FilmesJaWS.DAO" %>

<%
    string pIDFavorito = "IDFAVORITO";

    DAOFavorito favorito = new DAOFavorito();
    string IDFavorito = "3";

    if (Request.Form[pIDFavorito] != null)
    {
        IDFavorito = Request.Form[pIDFavorito].ToString();
    }

    Response.Write(favorito.getFavoritosConta(int.Parse(IDFavorito)));
    %>
