// カードをクリックしたら反転させる
document.querySelector('.flashcard-container').addEventListener('click', function() {
    this.querySelector('.card').classList.toggle('is-flipped');
});

let currentIndex = 0;

// 問題数の更新
function updateQuestionNumber() {
    const questionNumber = document.getElementById("question-number");
    questionNumber.textContent = `${currentIndex + 1}問目 / ${wordList.length}問中`;
}

// 単語を表示
function displayWord() {
    const word = wordList[currentIndex];
    
    // 単語名、意味、詳細説明の表示
    const wordNameElements = document.getElementsByClassName("word-name-container");
    for (let i = 0; i < wordNameElements.length; i++) {
        wordNameElements[i].textContent = word.name;
    }
    document.getElementById("word-describe").textContent = word.describe;
    document.getElementById("word-detail_describe").textContent = word.detail;

    // 問題数を更新
    updateQuestionNumber();
}

// 次へボタンを押したときの動作
document.getElementById("next-btn").addEventListener("click", function() {
    // 次の単語に進む
    currentIndex++;
    const card = document.querySelector(".card");
    card.style.transition = 'none';
    card.classList.remove("is-flipped");
     // 少し待ってから、transitionを再有効化
    setTimeout(function() {
        card.style.transition = ''; // アニメーションを元に戻す
    }, 10); // 少しだけ待ってからアニメーションを元に戻す

    // リストの最後に達したとき
    if (currentIndex < wordList.length) {
        displayWord(); // 次の単語を表示
    } else {
        document.getElementById("next-btn").disabled = true; // 次へボタンを無効化
    }
});

// 最初の単語を表示
displayWord();
