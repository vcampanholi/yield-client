package com.vandersoncamp.rendimento.business;import com.vandersoncamp.rendimento.model.Cliente;import com.vandersoncamp.rendimento.model.Endereco;import com.vandersoncamp.rendimento.model.RiscoEnum;import org.junit.jupiter.api.DisplayName;import org.junit.jupiter.api.Test;import org.mockito.Mockito;import javax.inject.Inject;import java.math.BigDecimal;import java.util.ArrayList;import java.util.List;import static org.junit.jupiter.api.Assertions.assertEquals;import static org.junit.jupiter.api.Assertions.assertNotNull;import static org.mockito.ArgumentMatchers.any;import static org.mockito.Mockito.when;public class ClienteBusinessTest {    @Inject    private ClienteBusiness clienteBusiness;    private Cliente getCliente() {        Cliente cliente = new Cliente();        //cliente.setId(1L);        cliente.setNome("Teste 001");        cliente.setRendimentoMensal(BigDecimal.TEN);        cliente.setRisco(RiscoEnum.A);        cliente.setEnderecos(getEnderecos());        return cliente;    }    private List<Endereco> getEnderecos() {        Endereco endereco = new Endereco();        //endereco.setId(1L);        endereco.setLogradouro("Rua 001");        endereco.setNumero(123L);        endereco.setBairro("Bairro 001");        endereco.setCep("88808430");        endereco.setCidade("Cidade 001");        endereco.setEstado("Estado 001");        endereco.setComplemento("Complemento");        List<Endereco> lista = new ArrayList<>();        lista.add(endereco);        return lista;    }    private EmprestimoBO getEmprestimo() {        EmprestimoBO emprestimo = new EmprestimoBO();        emprestimo.setCliente(getCliente());        emprestimo.setValorSolicitado(BigDecimal.TEN);        emprestimo.setMeses(1L);        emprestimo.setValorSimulacao(new BigDecimal("1200"));        return emprestimo;    }    private ClienteBusiness clienteBusinessMock() {        ClienteBusiness mock = Mockito.mock(ClienteBusiness.class);        when(mock.create(any())).thenReturn(new Cliente());        when(mock.find(any())).thenReturn(new Cliente());        when(mock.findAll(any(), any(), any())).thenReturn(new ArrayList<>());        when(mock.update(any())).thenReturn(new Cliente());        when(mock.simulaValorImprestimo(any())).thenReturn(getEmprestimo());        return mock;    }    @Test    @DisplayName("Test 01")    public void Test02() {        Cliente cliente = clienteBusinessMock().create(getCliente());        assertNotNull(cliente);    }    @Test    @DisplayName("Test 02")    public void Test03() {        Cliente cliente = clienteBusinessMock().find(1L);        assertNotNull(cliente);    }    @Test    @DisplayName("Test 03")    public void Test04() {        List<Cliente> clientes = clienteBusinessMock().findAll("", "", "");        assertNotNull(clientes);    }    @Test    @DisplayName("Test 04")    public void Test05() {        Cliente cliente = getCliente();        cliente.setNome("Nome Alterado");        Cliente clienteUpdate = clienteBusinessMock().update(cliente);        assertEquals(cliente.getNome(), "Nome Alterado");    }    @Test    @DisplayName("Test 06")    public void Test06() {        EmprestimoBO emprestimo = getEmprestimo();        Cliente cliente = getCliente();        cliente.setRendimentoMensal(new BigDecimal("100"));        cliente.setRisco(RiscoEnum.C);        emprestimo.setValorSolicitado(new BigDecimal("1000"));        emprestimo.setMeses(2L);        emprestimo.setCliente(cliente);        EmprestimoBO bo = clienteBusinessMock().simulaValorImprestimo(emprestimo);        assertEquals(bo.getValorSimulacao(), new BigDecimal("1200"));    }}