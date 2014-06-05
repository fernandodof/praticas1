function carregarNumeros(element) {
    var numsArray = [];

    if (document.getElementById('aposta').value !== '') {
        numsArray = document.getElementById('aposta').value.split(",");
    }

    if (numsArray.indexOf(element.value) === -1) {
        numsArray.push(element.value);
        element.style.color = 'blue';
        element.style.fontWeight = 'bold';
    } else {
        numsArray.splice(numsArray.indexOf(element.value), 1);
        element.disable = false;
        element.style.color = 'black';
        element.style.fontWeight = 'normal';
    }

    var numuerosDeBotaos = document.getElementsByClassName('btNumero');
    if (numsArray.length === 6) {
        for (i = 0; i < numuerosDeBotaos.length; i++) {
            if (numsArray.indexOf(numuerosDeBotaos[i].id) === -1) {
                numuerosDeBotaos[i].disabled = true;
                numuerosDeBotaos[i].style.color = 'grey';
                numuerosDeBotaos[i].style.fontWeight = 'normal';
            }
        }
    } else {
        for (i = 0; i < numuerosDeBotaos.length; i++) {
            if (numsArray.indexOf(numuerosDeBotaos[i].id) === -1) {
                numuerosDeBotaos[i].disabled = false;
                numuerosDeBotaos[i].style.color = 'black';
                numuerosDeBotaos[i].style.fontWeight = 'normal';
            }
        }
    }
    numsArray.sort(function(a, b){return a-b});
    document.getElementById('aposta').value = numsArray;
}