/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.df.sutic.cesta.controllers;

import java.io.Serializable;

/**
 *
 * @author welber.fernandes
 */
public class AcessoController implements  Serializable{
    /*
    @Inject
    private UsuarioFacade uf;
    private Acesso acesso;
    private Comissao comissao;
    private String cpf, novaSenha, senhaParidade, senha;
    private Usuario usuario;
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException ade) throws IOException, ServletException
    {
        if (getAuthentication() != null && !(getAuthentication() instanceof
                AnonymousAuthenticationToken))
        {
            if (getAuthentication().getAuthorities().stream().anyMatch(x -> x.
                    getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")))
                response.sendRedirect(request.getContextPath() +
                        "/main/administracao.xhtml");
            if (getAuthentication().getAuthorities().stream().anyMatch(x -> x.
                    getAuthority().equalsIgnoreCase("ROLE_GERENTE")))
                response.sendRedirect(request.getContextPath() +
                        "/main/gestao.xhtml");
            if (getAuthentication().getAuthorities().stream().anyMatch(x -> x.
                    getAuthority().equalsIgnoreCase("ROLE_PARTICIPANTE")))
                response.sendRedirect(request.getContextPath() +
                        "/main/participante.xhtml");
            if (getAuthentication().getAuthorities().stream().anyMatch(x -> x.
                    getAuthority().equalsIgnoreCase("ROLE_AVALIADOR")))
                response.sendRedirect(request.getContextPath() + 
                        "/main/avaliador.xhtml");
        }
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication) throws
            IOException, ServletException
    {
        AcessoController ac = CDI.current().select(AcessoController.class).
                get();
        ComissaoFacade cf = CDI.current().select(ComissaoFacade.class).get();
        UsuarioFacade uf = CDI.current().select(UsuarioFacade.class).get();
        Usuario usuario = uf.findByCPF(authentication.getName());
        usuario.registrarAcesso();
        ac.setUsuario(usuario);
        uf.edit(usuario);
        if (usuario.isAvaliador())
            ac.setComissao(cf.findForAvaliador(usuario));
        if (authentication.getAuthorities().stream().anyMatch(x -> x.
                getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR")))
            response.sendRedirect(request.getContextPath() +
                    "/main/administracao.xhtml");
        else if (authentication.getAuthorities().stream().anyMatch(x -> x.
                getAuthority().equalsIgnoreCase("ROLE_GERENTE")))
            response.sendRedirect(request.getContextPath() +
                    "/main/gestao.xhtml");
        else
            response.sendRedirect(request.getContextPath() +
                    "/main/participante.xhtml");
    }
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Acesso getAcesso() { return acesso; }
    public Comissao getComissao() { return comissao; }
    public String getCpf() { return cpf; }
    public String getNovaSenha() { return novaSenha; }
    public String getSenha() { return senha; }
    public String getSenhaParidade() { return senhaParidade; }
    public UsuarioFacade getUf() { return uf; }
    public Usuario getUsuario() { return usuario; }
    public void setAcesso(Acesso acesso) { this.acesso = acesso; }
    public void setComissao(Comissao comissao) { this.comissao = comissao; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setNovaSenha(String novaSenha) { this.novaSenha = novaSenha; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setSenhaParidade(String senhaParidade)
    {
        this.senhaParidade = senhaParidade;
    }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    //</editor-fold>
    /** 
     * Retorna a autenticação corrente.
     * @return autenticação
     */
//    private Authentication getAuthentication()
//    {
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
//    /**
//     * Retorna se o acesso é anônimo.
//     * @return false se o acesso não for anônimo ou true caso seja
//     */
//    public boolean isAnonimo()
//    {
//        return getAuthentication() == null || !getAuthentication().
//                isAuthenticated() || getAuthentication() instanceof
//                AnonymousAuthenticationToken;
//    }
//    /**
//     * Encerra uma sessão de acesso ao sistema.
//     * @return caminho a ser seguido pelo sistema
//     */
//    public String sair()
//    {
//        FacesContext.getCurrentInstance().getExternalContext().
//                invalidateSession();
//        setUsuario(null);
//        JSF.addSuccessMessage("saidaEfetuada");
//        return "login";
//    }
//    /**
//     * Altera a senha do usuário atualmente autenticado.
//     */
//    public void alterarSenha()
//    {
//        if (!getUsuario().getSenha().equalsIgnoreCase(Senhas.hash(getUsuario().
//                getCpf(), getSenha())))
//            JSF.addValidationError("atualIncorreta");
//        if (!getNovaSenha().equalsIgnoreCase(getSenhaParidade()))
//            JSF.addValidationError("confirmacaoIncorreta");
//        if (JSF.hasErrors())
//            return;
//        getUsuario().gerarSenha(getNovaSenha());
//        getUf().edit(getUsuario());
//        JSF.addSuccessMessage("senhaAlterada");
//    }
//    /**
//     * Gera uma nova senha para o usuário com o CPF especificado.
//     * @param cpf cpf do usuário
//     * @return caminho a seguir no sistema
//     */
//    public void resetarSenha(String cpf)
//    {
//        Usuario usuario = getUf().findByCPF(cpf);
//        if (usuario != null)
//        {
//            String senha = Senhas.gerar(Usuario.TAMANHO_SENHA);
//            usuario.gerarSenha(senha);
//            getUf().edit(usuario);
//            Mail.enviarSenha(senha, usuario);
//        }
//        JSF.addSuccessMessage("senhaRecuperada");
//    }

}
