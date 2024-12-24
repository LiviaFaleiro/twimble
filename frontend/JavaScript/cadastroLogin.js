// login
function fazerLogin() {
    const usuario = $("#usuario").val();
    const senha = $("#senha").val();

    fetch(`http://localhost:8080/usuarios`)
    .then(response => response.json())
    .then(usuariosBanco => {
        const usuarioEncontrado = usuariosBanco.find(u => u.nome === usuario && u.senha === senha);

        if (usuarioEncontrado) {
            usuarioAtual = {
                id: usuarioEncontrado.id,
                usuario: usuarioEncontrado.nome,
                email: usuarioEncontrado.email,
                senha: usuarioEncontrado.senha,
                tipo: usuarioEncontrado.tipo || "usuario"
            };

            alert(`Bem-vindo, ${usuarioAtual.usuario}`);
            $("#modal-login").hide();
            $("#modal-registro").hide();
            $("#main-screen").show();
            
            loadPosts(); // Carrega os posts iniciais
        } else {
            alert("Usuário ou senha inválidos. Por favor, tente novamente.");
        }
    });
}



//cadastro
function fazerRegistro() {
    const nome = document.getElementById('usuario-registro').value;
    const email = document.getElementById('email-registro').value;
    const senha = document.getElementById('senha-registro').value;

    const novoUsuario = {
        nome: nome,
        email: email,
        senha: senha,
        tipo: "usuario"
    };

    fetch('http://localhost:8080/usuario/cadastrar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(novoUsuario)
    })
    .then(response => response.json())
    .then(data => {
        alert('Cadastro realizado com sucesso!');
        login(); // Redireciona para a tela de login
    })
    .catch(error => {
        console.error('Erro:', error);
        alert('Erro ao realizar cadastro');
    });
}



function alternarModalLogin() {
    $("#modal-login").toggle();
}


function alternarModalRegistro() {
    $("#modal-registro").toggle();
}