# Alterações em DTOs
  CalculationResultDTO
    Substitui: CarbonCalculationResultDTO
    Motivo: Para melhorar a clareza e a estrutura dos dados retornados, separando a emissão total e a quebra por tipo de emissão.
  UserInfoDTO
    Substitui: StartCalcRequestDTO
    Motivo: Para adicionar validações e garantir que os dados de entrada sejam corretos.
  CalculationResponseDTO
    Substitui: StartCalcResponseDTO
    Motivo: Para fornecer mais informações sobre o estado do cálculo e a data de criação.
  CalculationInfoDTO
    Substitui: UpdateCalcInfoRequestDTO
    Motivo: Para adicionar validações e garantir que os dados de entrada sejam corretos, para centralizar todas as informações necessárias para o cálculo 
    em um único DTO, para simplificar a resposta e fornecer mais informações sobre o estado do cálculo.
    Substitui: TransportationDTO
    Motivo: Para simplificar a estrutura dos dados e evitar a necessidade de um DTO separado para transporte.
# Novos DTOs
  EmissionBreakdownDTO
    Contém: campos energyEmission, transportEmission, e wasteEmission.
    Motivo: Para fornecer uma quebra detalhada das emissões por tipo.
  ErrorResponse
    Contém: campos message e details.
    Motivo: Para fornecer uma resposta estruturada para erros de validação e recursos não encontrados.
# Exceções
  GlobalExceptionHandler
    Adicionado: Para lidar com exceções de validação e recursos não encontrados.
    Motivo: Para centralizar o tratamento de erros e fornecer respostas estruturadas.
  ResourceNotFoundException
    Adicionado: Para lançar exceções quando recursos não são encontrados.
    Motivo: Para fornecer uma resposta clara quando um recurso não é encontrado.
# Model
  CarbonCalculation
    Adicionado: Para representar a entidade de cálculo de carbono.
    Motivo: Para armazenar todas as informações necessárias para o cálculo de carbono.
# Repositórios
  CarbonCalculationRepository
    Adicionado: Para acessar a entidade CarbonCalculation.
    Motivo: Para fornecer acesso ao banco de dados para a entidade de cálculo de carbono.
# Serviços
  CarbonCalculatorService
    Adicionado: Para implementar a lógica de negócio para iniciar, atualizar e obter resultados de cálculos.
    Motivo: Para centralizar a lógica de negócio e separar a lógica de negócio da lógica de apresentação.
  EmissionCalculator
    Interface adicionada: Para definir o contrato para calcular emissões.
    Motivo: Para fornecer uma interface comum para diferentes tipos de cálculos de emissão.
  EnergyEmissionCalculator
    Implementação adicionada: Para calcular emissões de energia.
    Motivo: Para encapsular a lógica de cálculo de emissões de energia.
  TransportEmissionCalculator
    Implementação adicionada: Para calcular emissões de transporte.
    Motivo: Para encapsular a lógica de cálculo de emissões de transporte.
  WasteEmissionCalculator
    Implementação adicionada: Para calcular emissões de resíduos sólidos.
    Motivo: Para encapsular a lógica de cálculo de emissões de resíduos sólidos.
# Controladores
  OpenRestController
    Atualizado: Para implementar os métodos startCalculation, updateCalculationInfo, e getResult.
    Motivo: Para fornecer endpoints para iniciar, atualizar e obter resultados de cálculos.
# Padrões de Design Aplicados
  Repository Pattern: Utilizado para abstrair o acesso ao banco de dados e fornecer uma interface consistente para operações de CRUD.
  
  Service Layer Pattern: Utilizado para centralizar a lógica de negócio e separar a lógica de negócio da lógica de apresentação.
  
  Strategy Pattern: Utilizado para definir uma família de algoritmos (cálculos de emissão) e torná-los intercambiáveis. Cada tipo de cálculo de 
  emissão é uma estratégia diferente.
  
# Conceitos SOLID Aplicados
  Single Responsibility Principle (SRP):
    Cada classe tem uma única responsabilidade. Por exemplo, CarbonCalculatorService é responsável pela lógica de negócio, enquanto OpenRestController é 
    responsável pela lógica de apresentação.
  
  Open/Closed Principle (OCP):
    As classes são abertas para extensão, mas fechadas para modificação. Por exemplo, a interface EmissionCalculator permite adicionar novos tipos de 
    cálculos de emissão sem modificar a lógica existente.
  
  Liskov Substitution Principle (LSP):
    As subclasses devem ser substituíveis por suas superclasses. Por exemplo, qualquer implementação de EmissionCalculator pode ser usada onde 
    EmissionCalculator é esperado.
  
  Interface Segregation Principle (ISP):
    As interfaces são específicas para os clientes que as usam. Por exemplo, EmissionCalculator é uma interface específica para cálculos de emissão.
  
  Dependency Inversion Principle (DIP):
    As dependências são invertidas para que as classes de alto nível não dependam de classes de baixo nível. Por exemplo, CarbonCalculatorService depende 
    de EmissionCalculator, que é uma abstração.
