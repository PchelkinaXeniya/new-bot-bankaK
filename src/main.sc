require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день! Я виртуальный помощник банка «Рога и Копыта»!
            Чем могу помочь?
        intent: /Хочу заказать карту || toState = "/ШАГ2"
        event: noMatch || toState = "./"

    state: ШАГ2
        a: Позвольте уточнить, для каких целей вам нужна карта:
            - оплата в магазинах;
            -снятие наличных;  
            - зачисление социальных выплат или пенсии;
            - зачисление заработной платы;
            - другое. || htmlEnabled = true, html = "Позвольте уточнить, для каких целей вам нужна карта:<br><br>- оплата в магазинах;<br>-снятие наличных;  <br>- зачисление социальных выплат или пенсии;<br>- зачисление заработной платы;<br>- другое."
        intent: /зачисление социальных выплат или пенсии || toState = "/ШАГ3"
        intent: /карта для зачисления детских || toState = "./"
        intent: /карта для получения детских пособий || toState = "./"
        intent: /карта для получения пособий || toState = "./"
        intent: /в вашем банке карты для соц платежек есть || toState = "./"
        intent: /Оплата в магазине, снятие наличных, зачисление заработной плат, другое || toState = "/ШАГ5"
        intent: /Пенсия || toState = "/ШАГ4"
        event: noMatch || toState = "./"

    state: ШАГ3
        a: В соответствии с законодательством (161 ФЗ) выплаты должны осуществляться на карты национальной платежной системы "МИР".
                Банк «Рога и Копыта» предлагает клиентам карты системы (МИР) типов.
                 Народная карта МИР. Ознакомиться с условиями можно по ссылке: https://www.example.com
                Универсальная карта МИР. Ознакомиться с условиями можно по ссылке: https://www.
                example.com || htmlEnabled = false, html = "В соответствии с законодательством (161 ФЗ) выплаты должны осуществляться на карты национальной платежной системы "МИР".<br>Банк «Рога и Копыта» предлагает клиентам карты системы (МИР) типов.<br><br> Народная карта МИР. Ознакомиться с условиями можно по ссылке: https://www.example.com<br><br>Универсальная карта МИР. Ознакомиться с условиями можно по ссылке: https://www.<br><br>example.com"
        Confirmation: 
            prompt =   Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 
        event: noMatch || toState = "./"

    state: Вопросы?
        Confirmation: 
            prompt = У вас остались вопросы?
            agreeState = /Оператор
            disagreeState = /ШАГ18
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ17
        a: Я предлагаю оформить заявку на сайте нашего банка: https://www.example.com
            Альтернативным способом заказа является посещение офиса.
            Благодарим за обращение в банк «Рога и Копыта»!
            
        go!: /Завершение

    state: Завершение
        EndSession: 
            actions = $jsapi.stopSession();

    state: ШАГ5
        a: Укажите, что для вас важнее:
            
            -кешбэк;
            
            -начисление процентов на остаток;
            -повышенный лимит снятия наличных;
            - бесплатное обслуживание. || htmlEnabled = true, html = "Укажите, что для вас важнее:<br><br>-кешбэк;<br><br>-начисление процентов на остаток;<br>-повышенный лимит снятия наличных;<br>- бесплатное обслуживание."
        intent: /Кешбэк || toState = "/ШАГ6"
        intent: /Начисление процентов || toState = "/ШАГ8"
        intent: /Бесплатное обслуживание || toState = "/ШАГ15"
        event: noMatch || toState = "./"

    state: ШАГ4
        a: В соответствии с законодательством (161 ФЗ) зачисление пенсии должно
            осуществляться на карты национальной платежной системы «МИР». Банк
            «Рога и Копыта» предлагает своим клиентам выгодные условия по Пенсионной карте «МИР».
            Ознакомиться с тарифами можно по ссылке: 
            
            https://www.example.com
        go!: /Завершение

    state: ШАГ6
        a: Предлагаю
            рассмотреть Универсальную карту (MasterCard/ VISA). Ознакомиться с условиями
            можно на нашем официальном сайте: https://www.example.com
        go!: /ШАГ7

    state: ШАГ7
        a: Также предлагаю рассмотреть альтернативный вариант - Премиальная карта (MasterCard Premium/VISA Premium). Ознакомиться с условиями можно на нашем официальном сайте: https://www.example.com
        Confirmation: 
            prompt =   Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ8
        a: На каждом тарифном плане установлены лимиты на снятие собственных денежных средств без комиссии в месяц. Укажите, какие лимиты вас интересуют:
            
            - снятие до 250 000 руб. в месяц без комиссии;
            
            - снятие до 500 000 руб. в месяц без комиссии;
            
            - снятие 1 000 000 руб. в месяц без комиссии.
            
             || htmlEnabled = true, html = "На каждом тарифном плане установлены лимиты на снятие собственных денежных средств без комиссии в месяц. Укажите, какие лимиты вас интересуют:<br><br>- снятие до 250 000 руб. в месяц без комиссии;<br><br>- снятие до 500 000 руб. в месяц без комиссии;<br><br>- снятие 1 000 000 руб. в месяц без комиссии.<br><br>"
        intent: /250000 || toState = "/ШАГ9"
        intent: /500000 || toState = "/ШАГ11"
        intent: /1000000 || toState = "/ШАГ13"
        event: noMatch || toState = "./"

    state: ШАГ9
        a: Предлагаю рассмотреть Универсальную карту (MasterCard/ VISA). Ознакомиться с условиями можно на нашем официальном сайте: https://www.
            example.com || htmlEnabled = false, html = "Предлагаю рассмотреть Универсальную карту (MasterCard/ VISA). Ознакомиться с условиями можно на нашем официальном сайте: https://www.<br>example.com"
        go!: /ШАГ10

    state: ШАГ11
        a: Предлагаю рассмотреть Премиальную карту (MasterCard Premium/VISA Premium). Ознакомиться с условиями можно на нашем официальном сайте: https://www.example.com
        go!: /ШАГ12

    state: ШАГ13
        a: Предлагаю рассмотреть Эксклюзивную карту (VISA Platinum). Ознакомиться с условиями можно на нашем официальном сайте: https://www.example.com
        go!: /ШАГ14

    state: ШАГ10
        a: Также предлагаю рассмотреть альтернативный вариант - Универсальная карта «МИР». Ознакомиться с условиями можно на нашем официальном сайте: https://www.example.com
        Confirmation: 
            prompt = Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ12
        a: Также предлагаю рассмотреть альтернативный вариант - Эксклюзивная карта (VISA Platinum). Ознакомиться с условиями можно на нашем официальном сайте: https://www.example.com
        Confirmation: 
            prompt = Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ14
        a: Также предлагаю рассмотреть альтернативный вариант - Премиальная карта (MasterCard Premium/VISA Premium). Ознакомиться с условиями можно на нашем официальном сайте:
            
            https://www.example.com
        Confirmation: 
            prompt = Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ15
        a: Банк «Рога и Копыта» предлагает клиентам карты системы «МИР» нескольких типов:
            
            - Народная карта «МИР». Ознакомиться с условиями можно по ссылке: https://www.
            
            example.com
            - Универсальная карта «МИР». Ознакомиться с условиями можно по ссылке: https://www.
            
            example.com
             || htmlEnabled = true, html = "Банк «Рога и Копыта» предлагает клиентам карты системы «МИР» нескольких типов:<br><br>- Народная карта «МИР». Ознакомиться с условиями можно по ссылке: https://www.<br><br>example.com<br>- Универсальная карта «МИР». Ознакомиться с условиями можно по ссылке: https://www.<br><br>example.com<br>"
        go!: /ШАГ16

    state: ШАГ16
        a: Если вы являетесь пенсионером, то предлагаю рассмотреть Пенсионную карту «МИР». Ознакомиться с тарифами можно на сайте: https://www.example.com
        Confirmation: 
            prompt = Вы готовы заказать карту?
            agreeState = /ШАГ17
            disagreeState = /Вопросы?
            useButtons = false
            agreeButton = 
            disagreeButton = 

    state: ШАГ18
        a: Благодарим за обращение в банк «Рога и Копыта»!
             || htmlEnabled = false, html = "Благодарим за обращение в банк «Рога и Копыта»!<br>"
        go!: /Завершение

    state: Оператор
        TransferToOperator: 
            titleOfCloseButton = 
            messageBeforeTransfer = 
            messageBeforeTransferHtml = 
            prechatAttributes = {}
            ignoreOffline = false
            messageForWaitingOperator = 
            messageForWaitingOperatorHtml = 
            sendMessageHistoryAmount = 
            sendMessagesToOperator = false
            actions = {}
            htmlEnabled = false
            destination = 