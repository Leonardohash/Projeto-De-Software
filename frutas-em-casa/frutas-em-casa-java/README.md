# Projeto Frutas em Casa

Implementação em Java do cenário do caso de uso de assinatura do sistema **Frutas em Casa**, seguindo a separação UML em:

- **Boundary/Fronteira:** interação com o usuário via console.
- **Controller/Controle:** coordena o fluxo do caso de uso.
- **Entity/Entidade:** classes de domínio do sistema.
- **Persistência:** gravação em arquivo `.txt`.

## Como compilar e executar

No terminal, dentro da pasta do projeto:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.com.frutasemcasa.Main
```

No Windows PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src/main/java/*.java).FullName
java -cp out br.com.frutasemcasa.Main
```

## Dados de teste

- Código SMS fixo para teste: `6661`
- Cartão pode ser qualquer número fictício.
- CVV pode ser qualquer código fictício.
- Nenhum dado real deve ser usado.

## Persistência

As assinaturas finalizadas são salvas no arquivo:

```text
dados/assinaturas.txt
```

