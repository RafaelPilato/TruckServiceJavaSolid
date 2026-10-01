# Trabalho - Princípios do SOLID com Java

Sistema de ordens de serviço de uma oficina mecânica para caminhões, desenvolvido para a disciplina de Padrões de Projetos. O domínio escolhido simula a abertura e finalização de ordens de serviço (OS), com cálculo de custo e notificação do motorista ao final do atendimento.

## Como executar

O ponto de entrada é a classe `Main`, que monta as dependências e simula dois cenários de atendimento. Compile e execute os arquivos dentro de `src/` com o JDK (versão 17 ou superior) a partir da classe `Main`.

## Estrutura de pacotes

- `dominio` — entidades de negócio: `Motorista`, `Caminhao`, `Mecanico` e `OrdemServico`. Concentram suas próprias regras de validação e de estado.
- `custeio` — cálculo do custo do serviço. Interface `CalculadoraCustoServico` com duas estratégias: `CustoPorHoraMecanico` (cobra por hora trabalhada) e `CustoGarantia` (serviço em garantia, custo zero).
- `notificacao` — envio de aviso ao motorista. Interface `CanalNotificacao` com duas implementações: `NotificacaoEmail` e `NotificacaoWhatsApp`.
- `repositorio` — persistência das ordens de serviço. Interface `OrdemServicoRepositorio` com implementação em memória, `OrdemServicoRepositorioMemoria`.
- `excecao` — hierarquia de exceções de negócio: `OrdemServicoException` (abstrata) com as filhas `OrdemServicoJaFinalizadaException` e `DadosInvalidosParaFinalizarException`.
- `servico` — orquestração dos casos de uso: `AberturaOrdemServicoService` (abre uma OS) e `FinalizacaoOrdemServicoService` (finaliza, calcula o custo e notifica o motorista).

## Onde cada princípio do SOLID aparece

**SRP (Responsabilidade Única)** — `OrdemServico.finalizar()` valida suas próprias regras de estado (já finalizada, dados obrigatórios, datas). `FinalizacaoOrdemServicoService` só orquestra: delega a validação para a entidade, o cálculo para `CalculadoraCustoServico` e o aviso para `CanalNotificacao`, sem fazer nenhuma dessas tarefas sozinho.

**OCP (Aberto/Fechado)** — novas formas de cobrança (`CustoPorHoraMecanico`, `CustoGarantia`) ou novos canais de notificação (`NotificacaoEmail`, `NotificacaoWhatsApp`) podem ser adicionadas criando uma nova classe que implementa a interface correspondente, sem alterar nenhuma classe já existente.

**LSP (Substituição de Liskov)** — qualquer implementação de `CalculadoraCustoServico` ou `CanalNotificacao` pode substituir outra sem quebrar `FinalizacaoOrdemServicoService`. O mesmo vale para a hierarquia de exceções: o `catch (OrdemServicoException e)` em `Main` trata ambas as exceções filhas de forma polimórfica.

**ISP (Segregação de Interfaces)** — as interfaces são pequenas e focadas em uma única responsabilidade (`CalculadoraCustoServico` e `CanalNotificacao` têm um único método cada), evitando obrigar uma implementação a depender de métodos que não usa.

**DIP (Inversão de Dependência)** — `AberturaOrdemServicoService` e `FinalizacaoOrdemServicoService` dependem apenas das interfaces (`OrdemServicoRepositorio`, `CalculadoraCustoServico`, `CanalNotificacao`), recebidas via construtor. As implementações concretas só são instanciadas em `Main`, que funciona como composition root do sistema.

## Cenários simulados em `Main`

1. Cobrança por hora trabalhada + notificação por e-mail.
2. Serviço em garantia (custo zero) + notificação por WhatsApp.
3. Tentativa de finalizar a mesma OS duas vezes, demonstrando o tratamento de exceção de negócio.