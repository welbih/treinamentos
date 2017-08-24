package br.gov.df.sutic.cesta.security;

import java.util.UUID;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Essa classe contém apenas métodos estáticos de geração e manipulação de 
 * senhas.
 * @author Bruno Santos
 */
public class Senhas
{
    /**
     * Encripta uma senha de acordo com um sal informado.
     * @param salt sal utilizado como entrada adicional para a criação da 
     * senha
     * @param senha senha original
     * @return senha encriptada
     */
    public static String encriptar (String salt, String senha)
    {
//        ShaPasswordEncoder spe = new ShaPasswordEncoder(512);
//        spe.setIterations(1024);
//        return spe.encodePassword(senha, salt);
        return "";
    }
    /**
     * Gera uma senha aleatória com o número de caracteres informado.
     * @param tamanho número de caracteres
     * @return senha aleatória
     */
    public static String gerar(int tamanho)
    {
        return UUID.randomUUID().toString().substring(0, tamanho).toUpperCase();
    }
}