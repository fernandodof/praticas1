function carregarNumeros(element) {
    var numsArray = [];

    if (document.getElementById('aposta').value !== '') {
        numsArray = document.getElementById('aposta').value.split(",");
    }

    if (numsArray.indexOf(element.value) === -1) {
        numsArray.push(element.value);
        element.style.color = 'blue';
    } else {
        numsArray.splice(numsArray.indexOf(element.value), 1);
        element.disable = false;
        element.style.color = 'black';
    }

    var numuerosDeBotaos = document.getElementsByClassName('btNumero');
    if (numsArray.length === 6) {
        for (i = 0; i < numuerosDeBotaos.length; i++) {
            if (numsArray.indexOf(numuerosDeBotaos[i].id) === -1) {
                numuerosDeBotaos[i].disabled = true;
                numuerosDeBotaos[i].style.color = 'grey';
            }
        }
    } else {
        for (i = 0; i < numuerosDeBotaos.length; i++) {
            if (numsArray.indexOf(numuerosDeBotaos[i].id) === -1) {
                numuerosDeBotaos[i].disabled = false;
                numuerosDeBotaos[i].style.color = 'black';
            }
        }
    }
    numsArray.sort();
    document.getElementById('aposta').value = numsArray;
}