
function login() {
    $("#modal-login").show();
    $("#modal-registro").hide();
    $("#main-screen").hide();
}

function cadastro() {
    $("#modal-login").hide();
    $("#modal-registro").show();
    $("#main-screen").hide();
}

function logout() {
    $("#main-screen").hide();
    $("#modal-login").show();
    usuarioAtual = null;
}
