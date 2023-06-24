
// let submitBtn = document.getElementById("submit-button");

// submitBtn.addEventListener("click", submitted);

function selection() {

    // Instantiate an new XHR Object
    const xhr = new XMLHttpRequest();

    const xhrSaveURL = new XMLHttpRequest();

    const xhrRandomUrl = new XMLHttpRequest();

    const xhrGetUserId = new XMLHttpRequest();

    var regUrl = "sample.com";

    var category = document.getElementById("category").value;

    let response = document.getElementById("response");
    let download_button = document.getElementById("download-button");
    // Open an obejct (GET/POST, PATH,
    // ASYN-TRUE/FALSE)
    var url = "https://api.unsplash.com/photos/random?client_id=JiouuZcbhMxy8L8Oi9vgQ3oavNekX6PqqhIK-vBj5M8&query=" + category + "&orientation=landscape";

    xhr.open("GET", url, true);

    // When response is ready
    xhr.onload = function () {
        if (xhr.status === 200) {

            // Changing string data into JSON Object
            obj = JSON.parse(xhr.responseText);

            var saveUrl = "http://localhost:8081/terms/" + category + "/urls/";


            // Getting the ul element


            regUrl = obj.urls.regular;
            var saveJsonString = {
                "url": regUrl
            };
            xhrSaveURL.open("POST", saveUrl, true);
            xhrSaveURL.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            
            xhrSaveURL.onload = function () {
                console.log(xhrSaveURL.responseText);
                sessionStorage.setItem("currentUrlId",Number(xhrSaveURL.responseText));
                html_generator(obj.urls.regular,sessionStorage.getItem("currentUrlId"));

                if(sessionStorage.getItem("userLoggedIn") === null){
                    // if(true){
                        xhrGetUserId.open("GET","http://localhost:8081/userid",true);

                        xhrGetUserId.onload = function () {
                            sessionStorage.setItem("userLoggedIn",xhrGetUserId.responseText);
                            console.log(xhrGetUserId.responseText);
                        }
                        xhrGetUserId.send();
                        
                    }else{
                        console.log(sessionStorage.getItem("userLoggedIn"));
                    }
            }
            // str = '<img src="' + obj.urls.regular + '">';
            // download = '<form method="get" action="' + obj.urls.regular + '"><button type="submit">Download!</button></form>';
            // download2 = '<a href="'+obj.urls.regular+'" download="wallpaper.jpg" rel="noopener noreferrer" target="_blank">Download</a>';
            // download3 = '<form method="get" action="title-image.png"><button type="submit">Download!</button></form>';
            // console.log(str);
            // download_button.innerHTML = download2;
            // response.innerHTML = str;
            xhrSaveURL.send(JSON.stringify(saveJsonString));
            
            html_generator(obj.urls.regular);
        } else if (xhr.status === 403) {

            var randomUrl = "http://localhost:8081/terms/" + category + "/urls/random/";

            xhrRandomUrl.open("GET", randomUrl, true);

            xhrRandomUrl.onload = function () {
                if (this.status === 200) {

                    randomResponse = JSON.parse(this.responseText);

                    // str = '<img src="' + randomResponse.url + '">';
                    // download = '<form method="get" action="' + randomResponse.url + '"><button type="submit">Download!</button></form>';


                    // console.log(str);
                    // response.innerHTML = str;
                    // download_button.innerHTML = download;
                    console.log(randomResponse.id);
                    sessionStorage.setItem("currentUrlId",randomResponse.id);
                    if(sessionStorage.getItem("userLoggedIn") === null){
                    // if(true){
                        xhrGetUserId.open("GET","http://localhost:8081/userid",true);

                        xhrGetUserId.onload = function () {
                            sessionStorage.setItem("userLoggedIn",xhrGetUserId.responseText);
                            console.log(xhrGetUserId.responseText);
                        }
                        xhrGetUserId.send();
                        
                    }else{
                        console.log(sessionStorage.getItem("userLoggedIn"));
                    }
                    html_generator(randomResponse.url,randomResponse.id);
                    
                }
            }

            xhrRandomUrl.send();

        }
        else {
            console.log("File not found");
        }
    }

    // At last send the request
    xhr.send();
}

function save_image(x) {

    const xhrSaveFavorite = new XMLHttpRequest();
    const xhrUnsaveFavorite = new XMLHttpRequest();


    // x.classList.toggle("bi-heart-fill");
    // x.classList.toggle("bi-heart");

    var urlId = x.getAttribute("data-url-id");
    var saveFavoriteUrl = "http://localhost:8081/favs/"

//    let heart_state = document.getElementById("heart").getAttribute("class");
   let heart_state = x.getAttribute("class");
   if (heart_state=="bi-heart"){
        //call an api to save this photo
        // var urlId = sessionStorage.getItem("currentUrlId");
        var userId = sessionStorage.getItem("userLoggedIn");

        if (urlId !== null && userId !== null){

            var saveJsonString = {
                "urlId": Number(urlId),
                "userId": Number(userId)
            };
            xhrSaveFavorite.open("POST", saveFavoriteUrl, true);
            xhrSaveFavorite.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhrSaveFavorite.send(JSON.stringify(saveJsonString));
        }

        x.setAttribute("class","bi-heart-fill");
   }else{
        //call an api to unsave this photo
        // var urlId = sessionStorage.getItem("currentUrlId");
        var userId = sessionStorage.getItem("userLoggedIn");

        if (urlId !== null && userId !== null){

            var unsaveJsonString = {
                "urlId": Number(urlId),
                "userId": Number(userId)
            };
            xhrUnsaveFavorite.open("DELETE", saveFavoriteUrl, true);
            xhrUnsaveFavorite.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhrUnsaveFavorite.send(JSON.stringify(unsaveJsonString));
        }
        x.setAttribute("class","bi-heart");
   }

}

function html_generator(url,id) {
    let response = document.getElementById("response");
    let download_button = document.getElementById("download-button");

    let download_button_form = document.getElementById("download-button-form");
    let response_image = document.getElementById("response-image");

    let heart_icon = document.getElementById("heart");

    str = '<img src="' + url + '" class=".img-fluid" >';
    download = '<form method="get" action="' + url + '"><button type="submit" id = "Download" class="btn btn-primary">Download!</button></form>';
    download2 = '<a href="' + url + '" download="wallpaper.jpg" rel="noopener noreferrer" target="_blank">Download</a>';
    download3 = '<form method="get" action="title-image.png"><button type="submit">Download!</button></form>';
    console.log(str);
    // download_button.innerHTML = download;
    download_button_form.setAttribute("style", "display:block;");
    download_button_form.setAttribute("action", url);
    // response.innerHTML = str;
    response_image.setAttribute("style", "display:block;");
    response_image.setAttribute("src", url);
    response_image.setAttribute("data-url-id",id);

    heart_icon.setAttribute("style", "display:block;");
    heart_icon.setAttribute("class","bi-heart");
    heart_icon.setAttribute("data-url-id",id);


}

function favorites() {

    let favorites = document.getElementById("favs");


    const xhrGetFavs = new XMLHttpRequest();

    var url = "http://localhost:8081/favs/"+sessionStorage.getItem("userLoggedIn");
    xhrGetFavs.open("GET", url, true);
    var str="";
    var len=0;
    // str = '<img src="' + url + '" class=".img-fluid">';
    // When response is ready
    xhrGetFavs.onload = function () {

        

        obj = JSON.parse(xhrGetFavs.responseText);
        len = obj.length;
        for (let i = 0; i < len; i++) {

            str = str + '<div class="row">';

            str = str + '<div class="col-sm favsimgs"><img src="' + obj[i].url + '" class=".img-fluid" data-url-id='+obj[i].id+'><i onclick="save_image(this)" id="heart" class="bi-heart-fill" style="display:block" data-url-id='+obj[i].id+'></i></div>';
            i++;
            if (i<len){
                str = str + '<div class="col-sm favsimgs"><img src="' + obj[i].url + '" class=".img-fluid" data-url-id='+obj[i].id+'><i onclick="save_image(this)" id="heart" class="bi-heart-fill" style="display:block" data-url-id='+obj[i].id+'></i></div>';
            }else{
                str = str + '</div>';
                break;
            }
            i++;
            if (i<len){
                str = str + '<div class="col-sm favsimgs"><img src="' + obj[i].url + '" class=".img-fluid" data-url-id='+obj[i].id+'><i onclick="save_image(this)" id="heart" class="bi-heart-fill" style="display:block" data-url-id='+obj[i].id+'></i></div>';    
            }else{
                str = str + '</div>';
                break;
            }
            // str = str + '<img src="' + obj[i].url + '" class=".img-fluid">' + "\n";

            str = str + '</div>';
        }
        str = str + '</div>';

        console.log(str);
    // download_button.innerHTML = download;
    
    favorites.innerHTML = str;
    }

    xhrGetFavs.send();

}

function search_handler() {

    const xhr = new XMLHttpRequest();

    var search_string = document.getElementById("search-string").value;

    var url = "https://api.unsplash.com/photos/random?client_id=JiouuZcbhMxy8L8Oi9vgQ3oavNekX6PqqhIK-vBj5M8&query=" + search_string + "&orientation=landscape";

    xhr.open("GET", url, true);

    let resposne = document.getElementById("response");


    xhr.onload = function () {
        if (this.status === 200) {

            obj = JSON.parse(this.responseText);

            str = '<img src="' + obj.urls.regular + '">';
            console.log(str);
            resposne.innerHTML = str;

        } else if (this.status == 403) {
            console.log("403 rate limit exceeded");
            str = '<img src="./403-Forbidden-HTML.png">';
            resposne.innerHTML = str;
        }
        else {
            console.log("File not found");
        }
    }

    xhr.send();
}


function api_tester() {

    const xhr = new XMLHttpRequest();


    var term = document.getElementById("category").value;
    var regUrl = "adfadf.com";
    var jsonString = {
        "url": regUrl
    };

    var url = "http://localhost:8081/terms/" + term + "/urls/";

    let response = document.getElementById("response");

    xhr.open("POST",
        url, true);


    xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");

    xhr.onload = function () {
        if (this.status === 200) {

            // Changing string data into JSON Object
            obj = JSON.parse(this.responseText);

            // Getting the ul element
            // str = '<img src="' + obj.urls.regular + '">';


            str = '<p>' + obj.id + obj.term + '</p>';
            console.log(str);
            response.innerHTML = str;
            xhr.send(JSON.stringify(jsonString));

        }
        else if (this.status === 403) {

            console.log("here");



        }
        else {
            console.log("File not found");
        }
    }

    // At last send the request
    // xhr.send(JSON.stringify('{ "url" : '+regUrl +'}'));

}

function random_tester() {

    const xhrRandomUrl = new XMLHttpRequest();

    var term = document.getElementById("category").value;

    var randomUrl = "http://localhost:8081/terms/" + term + "/urls/random/";

    xhrRandomUrl.open("GET", randomUrl, true);

    xhrRandomUrl.onload = function () {
        if (this.status === 200) {

            randomResponse = JSON.parse(this.responseText);

            str = '<p> ' + randomResponse.url + '</p>';
            console.log(str);
            response.innerHTML = str;

        }
    }

    xhrRandomUrl.send();
}
// function readTextFile() {

//     file="./abc.txt";
//     var rawFile = new XMLHttpRequest();
//     rawFile.open("GET", file, false);
//     rawFile.onreadystatechange = function () {
//         if (rawFile.readyState === 4) {
//             if (rawFile.status === 200 || rawFile.status == 0) {
//                 var allText = rawFile.responseText;
//                 alert(allText);
//             }
//         }
//     }
//     rawFile.send(null);
// }

// $.ajax({
//     type: "POST",
//     url: "~/pythoncode.py",
//     data: { param: text}
//   }).done(function( o ) {
//      // do something
//   });

// $(document).ready(function() {
//     $("button").click(function() {
//         // $.ajax({url: "geeks.txt", 
//         //         success: function(result) {
//         //     $("#h11").html(result);
//         // }});
//         text = "Hello";
//         $.ajax({
//             type: "POST",
//             url: "./test.py",
//             data: { param: text }
//         }).done(function (o) {
//             // do something
//             alert(text);
//         });
//         // alert("Hello");
//     });
// });

//this is a comment

