<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="InsertConta.aspx.cs" Inherits="FilmesJaWS.GUI.InsertConta" %>


<%@ Import Namespace="FilmesJaWS.DAO" %>

<%

	string pNome = "NOME";
	string pFoto = "FOTO";
	string pDataNasc = "DTNASCIMENTO";
	string pIDUsuario = "IDUSUARIO";
	string pIDIDIOMA = "IDIDIOMA";

	string nome = "";
	string foto = "";
	string DataNascimento = "";
	int idUsuario = 0;
	int idIdioma = 0;


	if (Request.Form[pNome] != null)
    {
        nome = Request.Form[pNome].ToString();
    }

    if (Request.Form[pFoto] != null)
    {
        foto = Request.Form[pFoto].ToString();
    }

    if (Request.Form[pDataNasc] != null)
    {
        DataNascimento = Request.Form[pDataNasc].ToString();
    }

    if (Request.Form[pIDUsuario] != null)
    {
        idUsuario = int.Parse(Request.Form[pIDUsuario].ToString());
    }

    if (Request.Form[pIDIDIOMA] != null)
    {
        idIdioma = int.Parse(Request.Form[pIDIDIOMA].ToString());
    }

    DAOConta d = new DAOConta();
    Response.Write(d.insertConta(nome, DataNascimento, idUsuario, idIdioma, foto));
     %>