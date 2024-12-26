function criarPost() {
    const texto = document.querySelector('.post-box textarea').value;
    const id_usuario = localStorage.getItem('userId');

    fetch(`http://localhost:8080/posts/criar/${id_usuario}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
        },
        body: `texto=${encodeURIComponent(texto)}&id_usuario=${id_usuario}`
    })
    .then(response => {
        if (response.ok) {
            swal("Sucesso!", "Post criado!", "success");
            document.querySelector('.post-box textarea').value = '';
            carregarPosts();
        }
    })
    .catch(error => console.error('Erro:', error));
}

function carregarPosts() {
    fetch('http://localhost:8080/posts/listar', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(posts => {
        const container = document.getElementById('posts-container');
        container.innerHTML = '';
        
        posts.forEach(post => {
            container.innerHTML += `
                <div class="post">
                    <div class="post-header">
                        <strong>@${post.nomeUsuario}</strong>
                    </div>
                    <p>${post.texto}</p>
                    <div class="post-actions">
                        <span>❤️ ${post.n_curtidas}</span>
                        <span>💬 ${post.n_comentarios}</span>
                    </div>
                </div>
            `;
        });        
    })
    .catch(error => {
        console.error('Erro ao carregar posts:', error);
    });
}
