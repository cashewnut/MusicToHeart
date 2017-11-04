

function replaceOnshowEmoji(number){
    switch(number) {
        case 0:
            document.getElementById('face_realtime').src='img/angry.png';
            break;
        case 1:
            document.getElementById('face_realtime').src='img/disgust.png';
            break;
        case 2:
            document.getElementById('face_realtime').src='img/fear.png';
            break;
        case 3:
            document.getElementById('face_realtime').src='img/happy.png';
            break;
        case 4:
            document.getElementById('face_realtime').src='img/sad.png';
            break;
        case 5:
            document.getElementById('face_realtime').src='img/surprise.png';
            break;
        case 6:
            document.getElementById('face_realtime').src='img/neutral.png';
            break;
        default:
            alert("输入表情序号不在范围内");
    }
}
