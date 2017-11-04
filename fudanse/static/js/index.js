// 需要丢给播放列表的数据
// var racks=[{
//     "track": 1,
//     "name": "1",
//     "length": "8:31",
//     "file": "1"
// },{
//     "track": 2,
//     "name": "2",
//     "length": "8:31",
//     "file": "2"
// }];

var mediaPath = 'music/';

jQuery(function ($) {
    var supportsAudio = !!document.createElement('audio').canPlayType;
    if (supportsAudio) {
        var playing = false;
        var zz = function(data,flag) {
            $('#plList').html("");
            var index = 0;

            var extension = '';
            var buildPlaylist = $.each(data, function (key, value) {
                var trackNumber = value.track;
                var trackName = value.name
                var trackLength = value.length;
                if (trackNumber.toString().length === 1) {
                    trackNumber = '0' + trackNumber;
                } else {
                    trackNumber = '' + trackNumber;
                }
                $('#plList').append(
                    '<li><div class="plItem"><div class="plNum">' + trackNumber + '.</div>' +
                    '<div class="plTitle">' + trackName + '</div>' +
                    '<div class="plLength">' + trackLength + '</div></div></li>');
            });
            var audio = $('#audio1').bind('play', function () {
                    playing = true;
                    $('#npAction').text('Now Playing...');
                }).bind('pause', function () {
                    playing = false;
                    $('#npAction').text('Paused...');
                }).bind('ended', function () {
                    $('#npAction').text('Paused...');
                    if ((index + 1) < data.length) {
                        //播放完一首之后，进行一下操作
                        index++;
                        loadTrack(index);
                        audio.play();
                    } else {
                        //整个list音乐播放结束之后，进行以下操作
                        audio.pause();
                        index = 0;
                        loadTrack(index);
                    }
            }).get(0);
            var btnPrev = $('#btnPrev').click(function () {
                if ((index - 1) > -1) {
                    index--;
                    loadTrack(index);
                    if (playing) {
                        audio.play();
                    }
                } else {
                    //如果现在已经是列表第一首，还要向前一首歌，那么就播放列表的第一首
                    audio.pause();
                    index = 0;
                    loadTrack(index);
                }
            });
            var btnNext = $('#btnNext').click(function () {
                if ((index + 1) < data.length) {
                    index++;
                    loadTrack(index);
                    if (playing) {
                        audio.play();
                    }
                } else {
                    //如果现在已经是列最后一首，用户又点了向后一首
                    audio.pause();
                    index = 0;
                    loadTrack(index);
                }
            });
            var li = $('#plList li').click(function () {
                var id = parseInt($(this).index());
                if (id !== index) {
                    playTrack(id);
                }
            });
            var loadTrack = function (id) {
                $('.plSel').removeClass('plSel');
                $('#plList li:eq(' + id + ')').addClass('plSel');
                $('#npTitle').text(data[id].name);
                index = id;
                audio.src = mediaPath + data[id].file + extension;
            };
            var playTrack = function (id) {
                loadTrack(id);
                audio.play();
            };
            if (flag) {
                loadTrack(index);
                audio.play();
            } else{
                index = -1;
            }
        };
    }

    getEmotionMusicListFromServer();

    //手动更新播放列表
    $("#testPlay").click(function(){
        getEmotionMusicListFromServer();
        //playTheMusic(racks);
    });

    function getEmotionMusicListFromServer(){
        $.ajax({
            type: "get",
            url: "http://10.141.212.24:14567/getSongs",
            contentType: "application/json",
            dataType: "json",
            xhrFields: {
                withCredentials: true
            },
            success: function (obj) {
                if(obj["status"] == true){
                    var songList = obj["songInfo"]["songNameList"];
                    var arrayObj = new Array(songList.length);
                    for(var i = 0;i < songList.length;i++){
                        var tempTrack = new Object();
                        tempTrack.track = i;
                        tempTrack.name = songList[i];
                        var min = Math.floor(Math.random()*3) + Math.floor(Math.random()*3);
                        var sec = Math.floor(Math.random()*30) + Math.floor(Math.random()*30) ;
                        tempTrack.length = min + ":" + sec;
                        tempTrack.file = songList[i];
                        arrayObj[i] = tempTrack;
                    }
                    playTheMusic(arrayObj);
                    //更换表情图片
                    replaceOnshowEmoji(obj["songInfo"]["emotionType"]);
                }else{
                    alert("报错信息：" + obj["message"])
                }
            },
            error: function(){
                alert("出错。")
            }
        });
    }
    function playTheMusic(songInfoList){
        zz(songInfoList,true);
    }
});

//initialize plyr
plyr.setup($('#audio1'), {});