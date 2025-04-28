package br.appLogin.appLogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.appLogin.appLogin.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findById(long id);
	
	@Query(value="select * from applogin.usuario where email = :email and senha = :senha", nativeQuery = true)
	public Usuario login (String email, String senha);
}