package com.Caio.vendas_app.Service;

import com.Caio.vendas_app.Entity.Pessoa;
import com.Caio.vendas_app.Repository.PessoaRepository;
import com.Caio.vendas_app.Security.PessoaUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaUserDetailsService implements UserDetailsService {
    private final PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Pessoa pessoa = pessoaRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return new PessoaUserDetails(pessoa);
    }
}