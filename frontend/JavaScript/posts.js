function criarPost() {
 //erro no criar postkkkkkkkkk
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
