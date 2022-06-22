package com.paulaproenca.cursomc.services;

import com.paulaproenca.cursomc.domain.Categoria;
import com.paulaproenca.cursomc.domain.Cidade;
import com.paulaproenca.cursomc.domain.Cliente;
import com.paulaproenca.cursomc.domain.Endereco;
import com.paulaproenca.cursomc.domain.enums.TipoCliente;
import com.paulaproenca.cursomc.dto.ClienteDto;
import com.paulaproenca.cursomc.dto.ClienteNewDto;
import com.paulaproenca.cursomc.repositories.ClienteRepository;
import com.paulaproenca.cursomc.repositories.EnderecoRepository;
import com.paulaproenca.cursomc.services.exceptions.DataIntegrityException;
import com.paulaproenca.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional
    public Cliente save(Cliente cli) {
        cli.setId(null);
        cli = clienteRepository.save(cli);
        Endereco endereco = new Endereco();

        List<Endereco> listaEndereco = cli.getEnderecos();

       for (Endereco end : listaEndereco) {
           enderecoRepository.save(end);
       }
        return cli;
    }

    public Cliente findById(Long id) {
       Optional<Cliente> clienteOptional = clienteRepository.findById(id);
       return clienteOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! id:" + id));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return clienteRepository.save(newObj);
    }

    public void deleteById(Long id) {
        try {
            findById(id);
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possivel excluir um cliente que possui produtos");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linePerPage, String orderby, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage, Sort.Direction.valueOf(direction), orderby);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDto clienteDto){
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail());
    }

    public Cliente fromDTO(ClienteNewDto objtDto) {
        Cliente cli = new Cliente(
                null,
                objtDto.getNome(),
                objtDto.getEmail(),
                objtDto.getCpfCnpj(),
                TipoCliente.toEnum(objtDto.getTipoCliente()));

        Cidade cidade = new Cidade(objtDto.getCidadeId());

        Endereco endereco = new Endereco(
                null,
                objtDto.getLogadouro(),
                objtDto.getNumero(),
                objtDto.getComplemento(),
                objtDto.getBairro(),
                objtDto.getCep(),
                cidade,
                cli
        );
        cli.getEnderecos().add(endereco);
        cli.getTelefones().add(objtDto.getTelefone1());
        if (objtDto.getTelefone2()!=null) {
            cli.getTelefones().add(objtDto.getTelefone2());
        }

        if (objtDto.getTelefone3()!=null) {
            cli.getTelefones().add(objtDto.getTelefone3());
        }
        return cli;
    }
    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
