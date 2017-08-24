package br.gov.df.sutic.cesta.controllers;

import br.gov.df.sutic.cesta.entities.Usuario;
import br.gov.df.sutic.cesta.persistence.UsuarioFacade;
import br.gov.df.sutic.cesta.web.JSF;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * 
 * @author Welber 
 */
@Named
@ViewScoped
public class UsuarioController implements Serializable
{
 
    private Usuario usuario;
    private String apelido;
    private String senha;
    private List<Usuario> usuarios;
    private List<Map<String, String>> apelidos;
    
    @Inject
    private UsuarioFacade usuarioFacade;
    
    /**
     * construtor chamando novas instacia
     * pelo metodo privado da classe. 
     */
    public UsuarioController() {
        novaInstancia();
        
    }
    
    /**
     * 
     * @param posicao, normalmente recebido
     * pelo ui:repeat durante a inserção 
     * de um novo input, quando o button 
     * adicionar apelido for clicado 
     */
    public void adicionarApelido(int posicao) {
        getApelidos().add(posicao, new HashMap<>());
    }
    
    /**
     * 
     * @param apelido, passado para remover 
     * o input com apelido ou vazio.
     */
    public void removerApelido(HashMap<String, String> apelido) {
        getApelidos().remove(apelido);
    }
    
    /**
     * 
     * @param usuario remove usuário.
     */
    public void removerUsuario(Usuario usuario) {
        getUsuarioFacade().remove(usuario);
    }
    
    /**
     * salva um novo usuário ou 
     * atualiza se ele estiver sendo editado.
     */
    public void salvar() {
        adicionaApelidoAoUsuario();
        if(usuario.getId() == null) {
            getUsuarioFacade().create(usuario);
            JSF.addSuccessMessage("Usuario salvo com sucesso!");
        } else {
            getUsuarioFacade().edit(usuario);
            JSF.addSuccessMessage("Usuario editado com sucesso!");
        }        
        novaInstancia();
    }  
    
    /**
     * preparar 
     * formulário para edição 
     * do usuário.
     */
    public void preparaEdicao() {
        setApelidos(new ArrayList<>());
        for (String apelido1 : getUsuario().getApelidos())
        {
            Map map = new HashMap();
            map.put("apelidado", apelido1);
            getApelidos().add(map);
        }
        getUsuario().setApelidos(new HashSet<>());
    }
    
    /**
     * adiciona apelidos da lista
     * temporaria para lista do usuario.
     */
    private void adicionaApelidoAoUsuario() {
        for(Map<String, String> apelido : getApelidos()) {
            getUsuario().adicionarApelido(apelido.get("apelidado"));
        }    
    }
    
    public boolean confirmarSenha() {
        return usuario.getSenha().equals(getSenha()) ? false : true;
    }
    
    /**
     * nova instância de objetos 
     * e variáveis. 
     */
    private void novaInstancia() {
        //setApelido(null);
        setUsuario(new Usuario());
        setApelidos(new ArrayList<>());
        adicionarApelido(0);
        setUsuarios(new ArrayList<>());
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getApelido() {
        return apelido;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    private UsuarioFacade getUsuarioFacade() {
        return usuarioFacade;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void setApelido(String apelido) {
        this.apelido = apelido;
    }
    
    public List<Map<String, String>> getApelidos() {
        return apelidos;
    }

    public void setApelidos(List<Map<String, String>> apelidos) {
        this.apelidos = apelidos;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getUsuarios() {
        return getUsuarioFacade().findAll();
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    //</editor-fold>
}
