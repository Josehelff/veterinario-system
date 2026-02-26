# Guia Rápido de Inicialização

## Pré-requisitos

- Java 11 ou superior
- Node.js 18+ e npm
- Maven 3.6+
- Angular CLI (será instalado automaticamente)

## Iniciar em 3 Passos

### 1️⃣ Iniciar o Backend

```bash
cd backend
mvn spring-boot:run
```

O backend estará rodando em `http://localhost:8080/api`

### 2️⃣ Iniciar o Frontend

Em outro terminal:

```bash
cd frontend/veterinario-app
npm install  # Apenas na primeira vez
ng serve
```

O frontend estará disponível em `http://localhost:4200`

### 3️⃣ Acessar a Aplicação

Abra seu navegador e acesse: **http://localhost:4200**

## 🎯 Fluxo Típico de Uso

### 1. Cadastrar Animais
- Acesse a aba "Animais"
- Clique em "+ Novo Animal"
- Preencha os dados (brinco, nome, tipo, sexo, data de nascimento, etc.)
- Clique em "Salvar"

### 2. Registrar Inseminação
- Acesse a aba "Inseminações"
- Clique em "+ Nova Inseminação"
- Selecione o animal (fêmea ativa)
- Preencha data da inseminação e reprodutor
- Clique em "Salvar"

### 3. Confirmar Prenhez
- Após o diagnóstico, volte à inseminação
- Atualize o resultado do diagnóstico para "PRENHA"
- O sistema criará automaticamente um registro de prenhez
- A previsão de nascimento será calculada automaticamente

### 4. Registrar Nascimento
- Quando o animal parir, registre o nascimento
- Preencha dados do filhote (brinco, sexo, peso)
- O sistema criará automaticamente o registro de desmame

### 5. Acompanhar Desmame
- Acesse o registro de desmame
- A previsão será calculada automaticamente
- Quando realizado, atualize o status e peso

### 6. Visualizar Estatísticas
- Acesse o Dashboard
- Veja todas as taxas e estatísticas em tempo real

## 📊 Exemplo de Dados

Para testar o sistema, você pode usar estes dados:

### Animal
- Brinco: `001`
- Nome: `Margarida`
- Tipo: `BOVINO`
- Sexo: `FEMEA`
- Data de Nascimento: `2020-01-15`
- Raça: `Holandesa`
- Peso: `450`

### Inseminação
- Data: `2026-02-01`
- Reprodutor: `Touro 01`

### Prenhez (após diagnóstico positivo)
- Data de Confirmação: `2026-02-15`
- Período de Gestação: `285` dias
- Previsão de Nascimento: `2026-11-26` (calculado automaticamente)

### Desmame
- Dias de Desmame: `90`
- Previsão de Desmame: Data do nascimento + 90 dias

## 🔍 Verificar se Está Tudo Funcionando

### Backend
```bash
curl http://localhost:8080/api/estatisticas
```

Você deve receber um JSON com as estatísticas.

### Frontend
Acesse `http://localhost:4200` no navegador. Você deve ver a interface com o menu de navegação.

## 🐛 Solução de Problemas

### Porta 8080 já em uso
```bash
# Mudar porta no backend (application.properties)
server.port=8081
```

### Porta 4200 já em uso
```bash
ng serve --port 4300
```

### Erro de CORS
O backend está configurado para aceitar requisições de `http://localhost:4200` e `http://localhost:3000`.

### Banco de dados não inicializa
O H2 é em memória, então os dados são perdidos ao reiniciar. Isso é esperado para desenvolvimento.

## 📚 Próximos Passos

1. Explore todas as funcionalidades na interface
2. Teste os cálculos automáticos
3. Verifique as taxas no Dashboard
4. Consulte o README.md para documentação completa

## 💡 Dicas

- O sistema calcula automaticamente datas de previsão
- Você pode customizar períodos de gestação e desmame
- As taxas são atualizadas em tempo real
- Use o Dashboard para acompanhar indicadores principais

Bom uso! 🎉
