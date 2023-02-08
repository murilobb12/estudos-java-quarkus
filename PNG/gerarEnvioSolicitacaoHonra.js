console.log("====================================================")
console.log("GERADOR DE ARQUIVO JSON - BBSolicitacaoHonra")
console.log("Desenvolvedor: Alessandro Carvalho")
console.log("Versão.......: 001 19/12/2021")
console.log("====================================================")
/*-----------------------------------------------------------------
-----------------------------------------------------------------*/

const fs = require('fs');
const readline = require('readline');

var solicitacao = {}
var indiceSolicitacao = -1;

const ws = {
    fileEnt: " ",
    fileSai: " ",
    fileErr: " ",
    temEnt: true,
    temErr: true,
    rl: "",
    /*-- Acumuladores --*/
    countOper: 0,
    countLine: 0,
    countGrav: 0,
    countRecu: 0,
    /*-- Guardas --*/
    inGrava: false,
    typeLine: 0,
    tipoDetalhe: 0,
    /*-- Guarda dos dados --*/
    idOperacaoAgente: " ",
    sistemaRepasse: " ",
    contratoRepase: " ",
    cnpj: " " ,
    qtLine: 0,      // Quantidade de linhas do Trailler do arquivo: comparar com countLine
    qtOper: 0,      // Quantidade de operações do Trailler do arquivo: comparar com countOper
    /*-- Objeto para o JSON --*/
    jsonData: { cnpjAgenteFinanceiro: " ", solicitacoesHonra: []},
    jsonRecus: [],   
}

/*================================*/
/*     GERACAO DO ARQUIVO JSON
/*================================*/
async function processLineByLine() {
/*================================*/

    /*-- Recupera os parametros da execução --*/
    //   1º Parametro:  data no formato AAAAMMDD
    //   2º Parametro:  previa numérico, exemplo: 1
    //   Exemplo execução: >node gerajson 20200717 1
   /* process.argv.forEach((val, index) => {
        if ( index === 2 ) { ws.parmData = val }
        if ( index === 3 ) { ws.parmPrevia = val }
    });
    console.log("Data......: ", ws.parmData);
    console.log("Previa....: ", ws.parmPrevia);
    console.log("----------------------------------------------------")

*/
    /*-- Abre os arquivos TXT e JSon de erros --*/
    //   Padrão de nome: FGIBBSolicitacoesHonra.txt
    //                   FGIErrosSolicitacoesHonra.txt
    //           20200721 -> Data do movimento invertida
    //           1        -> Previa
    //   Obs.: Arquivo gerado com a extenção "json"
    /*ws.fileEnt = "FGIenvio-" + ws.parmData + "-" + ws.parmPrevia + ".txt"
    ws.fileSai = "FGIenvio-" + ws.parmData + "-" + ws.parmPrevia + ".json"
    ws.fileErr = "FGIerros-" + ws.parmData + "-" + ws.parmPrevia + ".json"*/
    ws.fileEnt = "FGIenvioBBSolicitacaoHonra.txt"
    ws.fileSai = "FGIenvioBBSolicitacaoHonra.json"
    ws.fileErr = "FGIErrosSolicitacoesHonra.json"

    try {
        const fileStream = fs.createReadStream(ws.fileEnt);
        ws.rl = readline.createInterface({
            input: fileStream,
            crlfDelay: Infinity
        });
    } catch (e) {
        ws.temEnt = false
        // console.log(e)
    }

    try {
        ws.jsonRecus = require('./' + ws.fileErr);
    } catch (e) {
        ws.temErr = false
        // console.log(e)
    }

    console.log("Arquivo entrada: " + ws.fileEnt + ( ws.temEnt ? " - OK" : " *** ERRO AO ABRIR O ARQUIVO ***"))
    console.log("Arquivo erros..: " + ws.fileErr + ( ws.temErr ? " - OK" : " *** ERRO AO ABRIR O ARQUIVO ***"))
    console.log("Arquivo gerado.: " + ws.fileSai)
    console.log("----------------------------------------------------")


    /*-- Ler o arquivo de entrada e carregar o Objeto de dados --*/
    console.log("Gerando arquivo...")
    for await (const line of ws.rl) {
        ws.line = line
        ws.countLine++
        // console.log("Linha ", ws.countLine, ": ", ws.line)

        ws.typeLine = parseInt( ws.line.substr(0,1) )
        switch(ws.typeLine) {
            case 0:
                /*===========================================*/
                /*                0 - TRAILLER               */
                /*===========================================*/
                ws.cnpj = ws.line.substr(2,14)
                console.log("CNPJ Agente Financeiro...: ", ws.cnpj)
                ws.jsonData.cnpjAgenteFinanceiro = ws.cnpj
                break
            case 1:    
                ws.tipoDetalhe = parseInt( ws.line.substr(36,1))
                /*===========================================*/
                /*           1 - DADOS DA OPERACAO           */
                /*===========================================*/
                // console.log("*** Dados da Operação ***")
                /*-- Grava os dados da operação anterior --*/
                if ( ws.inGrava ) {                   
                    ws.countGrav++
                }
                /*-- Inicializa variaveis e incrementa contador --*/
                ws.countOper++
                /*-- Recupera os dados da operação --*/
                solicitacao = {}
                amortizacaoFluxo = {}
                ws.idOperacaoAgente = ws.line.substr(2,20)
                console.log("ws.idOperacaoAgente...: ", ws.idOperacaoAgente)
                /*-- Verifica se operação Recusada/com Erros --*/
                if ( validaSemErro( ws.idOperacaoAgente ) ) {
                    /*-- Operacao sem Recusa/Erros --*/
                    ws.inGrava = true
                } else {
                    /*-- Operacao com Recusa/Erros --*/
                    ws.inGrava = false
                    ws.countRecu++
                }
                solicitacao.idOperacaoAgenteCreditoLivre = ws.idOperacaoAgente
                //amortizacaoFluxo.idOperacaoAgente = ws.idOperacaoAgente
                //ws.tipoDetalhe = ws.line.substr(37,1) 
                
                
                if (ws.tipoDetalhe == 1) {   
                    indiceSolicitacao++
                    //solicitacao.sistemaRepasse = (parseInt(ws.line.substr(22,2)))
                    //solicitacao.sistemaRepasse = ws.line.substr(22,2)
                    //solicitacao.sistemaRepasse = ""
                    //console.log("Sistema Repasse ....: ", solicitacao.sistemaRepasse)
                    //solicitacao.contratoRepasse = ws.line.substr(24,11)
                    //solicitacao.contratoRepasse = ""
                    //console.log("Contrato Repasse ...: ", solicitacao.ContratoRepasse)
                    solicitacao.valorSaldoDevedorDePrincipal = ( parseInt( ws.line.substr(38,19) ) / 100 )
                    console.log("Saldo devedor princ.: ", solicitacao.valorSaldoDevedorDePrincipal)
                    solicitacao.valorSaldoDeJurosNaNormalidade = ( parseInt( ws.line.substr(57,19) ) / 100 )
                    console.log("Saldo Juros Norma...: ", solicitacao.valorSaldoDeJurosNaNormalidade)
                    solicitacao.valorEncargosMoratorios = ( parseInt( ws.line.substr(76,19) ) / 100 )
                    console.log("Saldo encg. Mora ...: ", solicitacao.valorEncargosMoratorios)
                    ws.jsonData.solicitacoesHonra.push(solicitacao)
                    solicitacao.amortizacoesAteADataDaSolicitacaoDeHonra = []
                    console.log( 'ws.jsonData.solicitacoesHonra: ',ws.jsonData.solicitacoesHonra)
                }

                if (ws.tipoDetalhe == 2) {                   
                    amortizacaoFluxo.dataVencimento = ( ws.line.substr(38,4) + "-" + ws.line.substr(43,2) + "-" + ws.line.substr(46,2) )
                    amortizacaoFluxo.valorDevido = ( parseInt( ws.line.substr(49,19) ) / 100 )
                    amortizacaoFluxo.valorRecebido = ( parseInt( ws.line.substr(69,19) ) / 100 )

                    ws.jsonData.solicitacoesHonra[indiceSolicitacao].amortizacoesAteADataDaSolicitacaoDeHonra.push(amortizacaoFluxo)
                   // ws.jsonData.amortizacoesAteADataDaSolicitacaoDeHonra.push(amortizacaoFluxo)
                   // console.log( 'ws.jsonData.amortizacoesAteADataDaSolicitacaoDeHonra: ',ws.jsonData.amortizacoesAteADataDaSolicitacaoDeHonra)
                }
               
                break          
            case 9:
                /*===========================================*/
                /*                 9 - HEADER                */
                /*===========================================*/
                /*-- Grava os dados da operação anterior --*/
                if ( ws.inGrava ) {
                    ws.countGrav++
                }
                /*-- Confere a quantidade de operações --*/
                // ws.countOper
                ws.qtLine = parseInt( ws.line.substr(1,9) )
                ws.qtOper = parseInt( ws.line.substr(10,9) )
                if ( !(ws.qtLine === ws.countLine) ) {
                    console.log("***** ERRO ***** => Linhas do arquivo: ", ws.qtLine, " Linhas processadas: ", ws.countLine)    
                }
                if ( !(ws.qtOper === ws.countOper) ) {
                    console.log("***** ERRO ***** => Operações do arquivo: ", ws.qtOper, " Operações processadas: ", ws.countOper)    
                }
                break
            default:
                console.log("***** ERRO ***** => Tipo de registro não previsto: ", ws.typeLine, " (Aceito somente 0, 1, 2, 3 e 9")
        }
    }

    /*========== Fim carga dos dados ==========*/
    /*-- Cria o arquivo de saida --*/
    var logger = fs.createWriteStream(ws.fileSai, { })
    

    /*-- Gera o JSON e grava o arquivo de Saida --*/
    var jsonFile = JSON.stringify(ws.jsonData, null, 2);       // Gerar Json com indentacao
    // var jsonFile = JSON.stringify(ws.jsonData);             // Gerar Json sem indentacao
    logger.write(jsonFile)
    logger.end() 


    console.log("----------------------------------------------------")
    console.log("*** Finalizada a geração do Arquivo ***")
    console.log("> Quantidade Linhas no Arquivo TXT......: ", ws.countLine)
    console.log("> Quantidade Operações processadas......: ", ws.countOper)
    console.log("> Quantidade Operações Gravadas JSon....: ", ws.countGrav)
    console.log("> Quantidade Operações Recusadas/Erros..: ", ws.countRecu)
}


processLineByLine()


/*================================*/
function validaSemErro( operacao ) {
/*================================*/
if ( ws.temErr ) {
        for ( i=0; i<(ws.jsonRecus.length); i++) {
            if ( operacao === ws.jsonRecus[i].idObjetoValidado ) {
                console.log("====> Operação recusada/com erros.....: ", operacao)
                return false        // operação encontrada no arquivo de recusas
            }
        }
    } 
    return true
}


/*==================*/
function isNumber(n) {
/*==================*/
    return !isNaN(parseFloat(n)) && isFinite(n);
}