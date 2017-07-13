<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<head>
    <c:url value="/" var="contextPath" />
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="icon" href="//cdn.shopify.com/s/files/1/0155/7645/t/177/assets/favicon.ico?11981592617154272979" type="image/ico" />
    <link href="https://plus.googlecom/108540024862647200608" rel="publisher"/>
    <title>Livros de Java, SOA, Android, iPhone, Ruby on Rails e muito mais - Casa do Código</title>
    <link href="${contextPath}resources/css/cssbase-min.css" rel="stylesheet" type="text/css" media="all" />
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet'/>
    <link href="${contextPath}resources/css/fonts.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/fontello-ie7.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/fontello-embedded.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/fontello.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/layout-colors.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/responsive-style.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/guia-do-programador-style.css" rel="stylesheet" type="text/css"  media="all"  />
    <link href="${contextPath}resources/css/produtos.css" rel="stylesheet" type="text/css"  media="all"  />
    <link rel="canonical" href="http://www.casadocodigo.com.br/" />
    <link href="${contextPath}resources/css/book-collection.css" rel="stylesheet" type="text/css" media="all" />
    <link href="${contextPath}resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}resources/css/bootstrap-theme.min.css" rel="stylesheet">
</head>
<body>
<header id="layout-header">
    <div class="clearfix container">
        <a href="${s:mvcUrl('HC#index').build()}" id="logo">
        </a>
        <div id="header-content">
            <nav id="main-nav">

                <ul class="clearfix">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li><a href="${s:mvcUrl('PC#list').build()}" rel="nofollow"><s:message code="header.listaProdutos" /></a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li><a href="${s:mvcUrl('PC#form').build()}" rel="nofollow"><s:message code="header.cadastroProdutos" /></a></li>
                    </sec:authorize>

                    <li><a href="${s:mvcUrl('CC#list').build()}" rel="nofollow"><s:message code="header.carrinho" arguments="${carrinhoCompras.quantidade}"/></a></li>

                    <li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow"><s:message code="header.sobreNos" /></a></li>

                    <li><a href="/pages/perguntas-frequentes" rel="nofollow"><s:message code="header.perguntasFrequentes" /></a></li>

                    <li style="margin: 0 5px">
                        <a href="?locale=en_US">
                            <img class="flag" src="https://lipis.github.io/flag-icon-css/flags/4x3/us.svg" alt="United States of America Flag">
                        </a>
                    </li>

                    <li style="margin: 0 5px">
                        <a href="?locale=pt_BR">
                            <img class="flag" src="https://lipis.github.io/flag-icon-css/flags/4x3/br.svg" alt="Brazil Flag">
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
    </div>
</header>
<nav class="categories-nav">
    <ul class="container">
        <li class="category"><a href="http://www.casadocodigo.com.br">Home</a></li>
        <li class="category"><a href="/collections/livros-de-agile">
            Agile </a></li>
        <li class="category"><a href="/collections/livros-de-front-end">
            Front End </a></li>
        <li class="category"><a href="/collections/livros-de-games">
            Games </a></li>
        <li class="category"><a href="/collections/livros-de-java">
            Java </a></li>
        <li class="category"><a href="/collections/livros-de-mobile">
            Mobile </a></li>
        <li class="category"><a
                href="/collections/livros-desenvolvimento-web"> Web </a></li>
        <li class="category"><a href="/collections/outros"> Outros </a></li>
    </ul>
</nav>