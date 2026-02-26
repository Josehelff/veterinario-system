# Sistema de Gerenciamento Veterinário

## 🏗️ Arquitetura

### Backend
- **Tecnologia**: Java 11 + Spring Boot 2.7.14
- **Banco de Dados**: H2 (em memória)
- **API**: REST
- **Porta**: 8080

### Frontend
- **Tecnologia**: Angular 18+
- **Estilo**: CSS 
- **Porta**: 4200

## 📁 Estrutura do Projeto

```
veterinario-system/
├── backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/veterinario/
│   │   │   │   ├── controller/        # Controllers REST
│   │   │   │   ├── entity/            # Entidades JPA
│   │   │   │   ├── repository/        # Repositórios
│   │   │   │   ├── service/           # Serviços de negócio
│   │   │   │   ├── dto/               # Data Transfer Objects
│   │   │   │   └── config/            # Configurações
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   └── pom.xml
├── frontend/
│   └── veterinario-app/
│       ├── src/
│       │   ├── app/
│       │   │   ├── components/        # Componentes Angular
│       │   │   ├── services/          # Serviços HTTP
│       │   │   ├── models/            # Modelos TypeScript
│       │   │   ├── app.ts             # Componente raiz
│       │   │   └── app.routes.ts      # Rotas
│       │   └── main.ts
│       ├── package.json
│       └── angular.json
└── README.md
```

## 🚀 Como Executar

### Backend

#### 1. Compilar o projeto
```bash
cd backend
mvn clean package -DskipTests
```

#### 2. Executar a aplicação
```bash
mvn spring-boot:run
```

Ou executar o JAR gerado:
```bash
java -jar target/veterinario-system-1.0.0.jar
```

A API estará disponível em: `http://localhost:8080/api`

#### 3. Acessar o H2 Console (opcional)
```
http://localhost:8080/api/h2-console
```

### Frontend

#### 1. Instalar dependências
```bash
cd frontend/veterinario-app
npm install
```

#### 2. Executar em desenvolvimento
```bash
ng serve
```

Ou com a porta específica:
```bash
ng serve --port 4200
```

A aplicação estará disponível em: `http://localhost:4200`

#### 3. Compilar para produção
```bash
ng build --configuration production
```

## 📊 Funcionalidades Principais

### 1. Cadastro de Animais
- Registrar animais com informações completas (brinco, nome, tipo, sexo, data de nascimento, raça, peso)
- Gerenciar status (ativo, inativo, vendido, falecido)
- Listar animais por tipo, sexo ou status

### 2. Controle de Inseminações
- Registrar inseminações com data, reprodutor e observações
- Acompanhar status (realizada, cancelada, repetida)
- Registrar resultado do diagnóstico (prenha, não prenha, pendente)

### 3. Controle de Prenhezes
- Confirmar prenhezes com período de gestação
- **Cálculo automático** de previsão de nascimento:
  - Período padrão: 285 dias ou 292 dias
  - Período customizável pelo usuário
- Acompanhar status (confirmada, abortada, finalizada)

### 4. Registro de Nascimentos
- Registrar nascimentos de filhotes
- Informações: brinco, nome, sexo, peso ao nascer
- Status do nascimento (vivo, morto, abortado)

### 5. Controle de Desmame
- **Cálculo automático** de previsão de desmame
- Períodos comuns: 90, 210, 240 dias (customizável)
- Acompanhar status (pendente, realizado, cancelado)
- Registrar peso ao desmame

### 6. Cálculos Automáticos e Taxas

#### Taxa de Concepção
```
Taxa de Concepção = (Total de vacas prenhas / Total de vacas inseminadas) × 100
```

#### Taxa de Prenhez
```
Taxa de Prenhez = (Quantidade de vacas prenhas / Quantidade de vacas que passaram no DG) × 100
```

#### Faltou no DG
```
Faltou no DG = Vacas inseminadas - Vacas que passaram no DG
```

Exemplo:
- 80 inseminadas
- 75 passaram no DG
- **Resultado: 5 faltaram**

## 🔌 Endpoints da API

### Animais
- `GET /api/animais` - Listar todos
- `GET /api/animais/{id}` - Obter por ID
- `GET /api/animais/brinco/{brinco}` - Obter por brinco
- `GET /api/animais/femeas/ativas` - Listar fêmeas ativas
- `GET /api/animais/machos/ativos` - Listar machos ativos
- `POST /api/animais` - Criar novo
- `PUT /api/animais/{id}` - Atualizar
- `DELETE /api/animais/{id}` - Deletar

### Inseminações
- `GET /api/inseminacoes` - Listar todas
- `GET /api/inseminacoes/{id}` - Obter por ID
- `GET /api/inseminacoes/animal/{animalId}` - Listar por animal
- `GET /api/inseminacoes/periodo?dataInicio=&dataFim=` - Listar por período
- `POST /api/inseminacoes` - Criar nova
- `PUT /api/inseminacoes/{id}` - Atualizar
- `DELETE /api/inseminacoes/{id}` - Deletar

### Prenhezes
- `GET /api/prenhezes` - Listar todas
- `GET /api/prenhezes/{id}` - Obter por ID
- `GET /api/prenhezes/animal/{animalId}` - Listar por animal
- `GET /api/prenhezes/confirmadas` - Listar confirmadas
- `GET /api/prenhezes/previsoes?dataInicio=&dataFim=` - Listar previsões por período
- `POST /api/prenhezes` - Criar nova
- `PUT /api/prenhezes/{id}` - Atualizar
- `DELETE /api/prenhezes/{id}` - Deletar

### Nascimentos
- `GET /api/nascimentos` - Listar todos
- `GET /api/nascimentos/{id}` - Obter por ID
- `GET /api/nascimentos/animal/{animalId}` - Listar por animal
- `GET /api/nascimentos/periodo?dataInicio=&dataFim=` - Listar por período
- `POST /api/nascimentos` - Criar novo
- `PUT /api/nascimentos/{id}` - Atualizar
- `DELETE /api/nascimentos/{id}` - Deletar

### Desmames
- `GET /api/desmames` - Listar todos
- `GET /api/desmames/{id}` - Obter por ID
- `GET /api/desmames/animal/{animalId}` - Listar por animal
- `GET /api/desmames/pendentes` - Listar pendentes
- `GET /api/desmames/periodo?dataInicio=&dataFim=` - Listar por período
- `POST /api/desmames` - Criar novo
- `PUT /api/desmames/{id}` - Atualizar
- `DELETE /api/desmames/{id}` - Deletar

### Estatísticas
- `GET /api/estatisticas` - Obter todas as estatísticas

## 📱 Interface do Usuário

### Dashboard
- Visualização geral de estatísticas
- Totais de animais, inseminações, prenhezes, nascimentos e desmames
- Taxas de concepção e prenhez
- Animais faltantes no DG

### Gerenciamento de Animais
- Tabela com todos os animais cadastrados
- Formulário para criar/editar animais
- Filtros por tipo, sexo e status

### Gerenciamento de Inseminações
- Tabela com inseminações
- Formulário para registrar inseminações
- Atualizar resultado do diagnóstico

### Gerenciamento de Prenhezes
- Tabela com prenhezes confirmadas
- Cálculo automático de previsão de nascimento
- Acompanhamento de status

### Gerenciamento de Nascimentos
- Tabela com nascimentos registrados
- Informações do filhote (brinco, sexo, peso)
- Status do nascimento

### Gerenciamento de Desmame
- Tabela com desmames
- Cálculo automático de previsão de desmame
- Registro de peso ao desmame

## 🗄️ Modelo de Dados

### Animal
- `id` (Long)
- `brinco` (String, único)
- `nome` (String)
- `tipo` (Enum: BOVINO, EQUINO, CAPRINO, OVINO, SUINO, OUTRO)
- `sexo` (Enum: MACHO, FEMEA)
- `dataNascimento` (LocalDate)
- `raca` (String)
- `peso` (Double)
- `observacoes` (String)
- `status` (Enum: ATIVO, INATIVO, VENDIDO, FALECIDO)

### Inseminação
- `id` (Long)
- `animal` (Foreign Key)
- `dataInseminacao` (LocalDate)
- `reprodutor` (String)
- `observacoes` (String)
- `status` (Enum: REALIZADA, CANCELADA, REPETIDA)
- `dataDiagnostico` (LocalDate)
- `resultadoDiagnostico` (Enum: PRENHA, NAO_PRENHA, PENDENTE)

### Prenhez
- `id` (Long)
- `animal` (Foreign Key)
- `inseminacao` (Foreign Key)
- `dataConfirmacao` (LocalDate)
- `periodoGestacao` (Integer)
- `previsaoNascimento` (LocalDate) - **Calculado automaticamente**
- `observacoes` (String)
- `status` (Enum: CONFIRMADA, ABORTADA, FINALIZADA)

### Nascimento
- `id` (Long)
- `animal` (Foreign Key)
- `prenhez` (Foreign Key)
- `dataNascimento` (LocalDate)
- `brincoFilhote` (String)
- `nomeFilhote` (String)
- `sexoFilhote` (Enum: MACHO, FEMEA)
- `pesoNascimento` (Double)
- `statusNascimento` (Enum: VIVO, MORTO, ABORTADO)
- `observacoes` (String)

### Desmame
- `id` (Long)
- `animal` (Foreign Key)
- `nascimento` (Foreign Key)
- `dataNascimento` (LocalDate)
- `diasDesmame` (Integer)
- `previsaoDesmame` (LocalDate) - **Calculado automaticamente**
- `dataDesmameReal` (LocalDate)
- `pesoDesmame` (Double)
- `observacoes` (String)
- `status` (Enum: PENDENTE, REALIZADO, CANCELADO)

## 🔐 Segurança

Atualmente, o sistema não possui autenticação.
- Implementar Spring Security
- Adicionar JWT para autenticação
- Validar requisições CORS

## 📝 Regras de Negócio

1. **Previsão de Nascimento**: Data da cobertura + período de gestação (285, 292 ou customizado)
2. **Previsão de Desmame**: Data do nascimento + dias definidos (90, 210, 240 ou customizado)
3. **Taxa de Concepção**: Calculada automaticamente com base em inseminações e prenhezes
4. **Taxa de Prenhez**: Calculada com base em diagnósticos realizados
5. **Faltou no DG**: Diferença entre inseminadas e que passaram no diagnóstico

## 🛠️ Tecnologias Utilizadas

### Backend
- Java 11
- Spring Boot 2.7.14
- Spring Data JPA
- H2 Database
- Lombok
- Maven

### Frontend
- Angular 18+
- TypeScript
- CSS3
- RxJS
- HttpClient


