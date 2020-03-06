using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FilmesJaWS.Model
{
    public class ModelUsuario
    {
        public int ID { get; set; }
        public string Telefone { get; set; }
        public string Senha { get; set; }
        public int SenhaCatalogo { get; set; }
        public string Email { get; set; }
        public string CPF { get; set; }
        public int Ativo { get; set; }
    }
}