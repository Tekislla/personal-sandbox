(function($) {
  "use strict";

  $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
    if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
      var target = $(this.hash);
      target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
      if (target.length) {
        $('html, body').animate({
          scrollTop: (target.offset().top - 71)
        }, 1000, "easeInOutExpo");
        return false;
      }
    }
  });

  $(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });

  $('.js-scroll-trigger').click(function() {
    $('.navbar-collapse').collapse('hide');
  });

  $('body').scrollspy({
    target: '#mainNav',
    offset: 80
  });

  var navbarCollapse = function() {
    if ($("#mainNav").offset().top > 100) {
      $("#mainNav").addClass("navbar-shrink");
    } else {
      $("#mainNav").removeClass("navbar-shrink");
    }
  };
  navbarCollapse();
  $(window).scroll(navbarCollapse);

  $(function() {
    $("body").on("input propertychange", ".floating-label-form-group", function(e) {
      $(this).toggleClass("floating-label-form-group-with-value", !!$(e.target).val());
    }).on("focus", ".floating-label-form-group", function() {
      $(this).addClass("floating-label-form-group-with-focus");
    }).on("blur", ".floating-label-form-group", function() {
      $(this).removeClass("floating-label-form-group-with-focus");
    });
  });

})(jQuery);


function orderPizzaP(){
  flavorsP = [{id: document.getElementById("flavorList1").value}]
  edgeP = {id: document.getElementById("edgeList1").value}

  fetch('http://localhost:2000/pizza/pizza/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({size: "P", flavor: flavorsP, edge: edgeP})
  }).then(response => response.json()).then((pizzaId) =>{
    fetch('http://localhost:2000/pizza/request/create/1/' + pizzaId, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
  alert("Pedido de pizza P recebido!")
  }
)
}
  

function orderPizzaM(){
  flavorsM = [{id: document.getElementById("flavorList2").value},{id: document.getElementById("flavorList3").value}]
  edgeM = {id: document.getElementById("edgeList2").value}

  fetch('http://localhost:2000/pizza/pizza/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({size: "M", flavor: flavorsM, edge: edgeM})
  }).then(response => response.json()).then((pizzaId) =>{
    fetch('http://localhost:2000/pizza/request/create/1/' + pizzaId, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
  alert("Pedido de pizza M recebido!")
  }
)
}


function orderPizzaG(){
  flavorsG = [{id: document.getElementById("flavorList4").value},{id: document.getElementById("flavorList5").value},{id: document.getElementById("flavorList6").value}]
  edgeG = {id: document.getElementById("edgeList3").value}

  fetch('http://localhost:2000/pizza/pizza/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({size: "G", flavor: flavorsG, edge: edgeG})
  }).then(response => response.json()).then((pizzaId) =>{
    fetch('http://localhost:2000/pizza/request/create/1/' + pizzaId, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
  alert("Pedido de pizza G recebido!")
  }
)
}


function orderPizzaGg(){
  flavorsGg = [{id: document.getElementById("flavorList7").value},{id: document.getElementById("flavorList8").value},{id: document.getElementById("flavorList9").value},{id: document.getElementById("flavorList10").value}]
  edgeGg = {id: document.getElementById("edgeList4").value}

  fetch('http://localhost:2000/pizza/pizza/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({size: "GG", flavor: flavorsGg, edge: edgeGg})
  }).then(response => response.json()).then((pizzaId) =>{
    fetch('http://localhost:2000/pizza/request/create/1/' + pizzaId, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
  alert("Pedido de pizza GG recebido!")
  }
)
}


function orderPizzaXg(){
  flavorsXg = [{id: document.getElementById("flavorList11").value},{id: document.getElementById("flavorList12").value},{id: document.getElementById("flavorList13").value},{id: document.getElementById("flavorList14").value},{id: document.getElementById("flavorList15").value}]
  edgeXg = {id: document.getElementById("edgeList5").value}

  fetch('http://localhost:2000/pizza/pizza/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({size: "XG", flavor: flavorsXg, edge: edgeXg})
  }).then(response => response.json()).then((pizzaId) =>{
    fetch('http://localhost:2000/pizza/request/create/1/' + pizzaId, {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  })
  alert("Pedido de pizza XG recebido!")
  }
)
}


function getFlavorsEdges(){
  fetch('http://localhost:2000/pizza/flavor/find-all', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).then(response => response.json()).then((flavors) =>{
      for (i=0; i < flavors.length; i++) {
        document.getElementById("flavorList1").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList2").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList3").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList4").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList5").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList6").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList7").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList8").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList9").innerHTML  += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList10").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList11").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList12").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList13").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList14").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
        document.getElementById("flavorList15").innerHTML += "<option value="+flavors[i]["id"]+" style='font-weight: bold;'><span>"+flavors[i]["name"]+"</span></option><option disabled><span>+ R$ " +flavors[i]["value"]+" - "+flavors[i]["description"]+"</span></option>";
      }
    }
  )
  fetch('http://localhost:2000/pizza/edge/find-all', {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).then(response => response.json()).then((edges) =>{
    for(i=0; i < edges.length; i++) {
      document.getElementById("edgeList1").innerHTML += "<option value="+edges[i]["id"]+" style='font-weight: bold;'><span>"+edges[i]["name"]+"</span></option><option disabled><span>+ R$ "+edges[i]["value"]+"</span></option>";
      document.getElementById("edgeList2").innerHTML += "<option value="+edges[i]["id"]+" style='font-weight: bold;'><span>"+edges[i]["name"]+"</span></option><option disabled><span>+ R$ "+edges[i]["value"]+"</span></option>";
      document.getElementById("edgeList3").innerHTML += "<option value="+edges[i]["id"]+" style='font-weight: bold;'><span>"+edges[i]["name"]+"</span></option><option disabled><span>+ R$ "+edges[i]["value"]+"</span></option>";
      document.getElementById("edgeList4").innerHTML += "<option value="+edges[i]["id"]+" style='font-weight: bold;'><span>"+edges[i]["name"]+"</span></option><option disabled><span>+ R$ "+edges[i]["value"]+"</span></option>";
      document.getElementById("edgeList5").innerHTML += "<option value="+edges[i]["id"]+" style='font-weight: bold;'><span>"+edges[i]["name"]+"</span></option><option disabled><span>+ R$ "+edges[i]["value"]+"</span></option>";
    }
    console.log("Sabores aqui!");
  }
)
}


function createUser(){
  fetch('http://localhost:2000/pizza/user/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({name: document.getElementsByName("userName")[0].value, email: document.getElementsByName("userEmail")[0].value, phone: document.getElementsByName("userPhone")[0].value, password: document.getElementsByName("userPassword")[0].value, street: document.getElementsByName("userStreet")[0].value, district: document.getElementsByName("userDistrict")[0].value, number: document.getElementsByName("userNumber")[0].value, complement: document.getElementsByName("userComplement")[0].value})
  }).then(response => response.json()).then((personId) =>{
    localStorage.setItem("id", personId)
    localStorage.setItem("login", document.getElementsByName("login")[0].value)
  })
  setTimeout(function(){ window.location.href="index-logged.html" }, 1500)
}


function addEdge(){
  fetch('http://localhost:2000/pizza/edge/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({name: document.getElementsByName("edgeName")[0].value, value: document.getElementsByName("edgeValue")[0].value})
  })
  alert("Edge created!");
  setTimeout(function(){ window.location.href="index-logged-admin.html" }, 1500)
}


function addFlavor(){
  fetch('http://localhost:2000/pizza/flavor/create', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({name: document.getElementsByName("flavorName")[0].value, description: document.getElementsByName("flavorDescription")[0].value, value: document.getElementsByName("flavorValue")[0].value})
  })
  alert("Flavor created!");
  setTimeout(function(){ window.location.href="index-logged-admin.html" }, 1500)
}


/*
function login(){
  let informedEmail = document.getElementsByName("userEmail")[0].value;
  let informedPassword = document.getElementsByName("userPassword")[0].value;

  fetch('http://localhost:2000/pizza/user/login/' + informedEmail + '/' + informedPassword, {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    }
  }).then(response => response.json()).then((loginResponse) =>{
    if (loginResponse == 0) {
      alert("Usuário não encontrado!")
    } else if (loginResponse == 400) {
      alert("Senha incorreta!")
    } else {
      localStorage.setItem("id",loginResponse)
      setTimeout(function(){ window.location.href="index-logged.html" }, 1500)
    }
    }
  )
}


function logout(){
  localStorage.clear();
  setTimeout(function(){ window.location.href="index.html" }, 1500);
}
*/