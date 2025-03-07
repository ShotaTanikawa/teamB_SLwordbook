
// カードをクリックしたら反転させる
document.querySelector('.flashcard-container').addEventListener('click', function() {
    this.querySelector('.card').classList.toggle('is-flipped');
});

let currentIndex = 0;

// 問題数の更新
function updateQuestionNumber(currentIndex) {
    const questionNumber = document.getElementById("question-number");
    questionNumber.textContent = `${currentIndex + 1}問目 / ${listWords.length}問中`;
}

// 単語を表示
function displayWord(currentIndex) {
    const listWord = listWords[currentIndex];
    
    // 単語名、意味、説明の表示
    const wordNameElements = document.getElementsByClassName("word-name-container");
    for (let i = 0; i < wordNameElements.length; i++) {
        wordNameElements[i].textContent = listWord[0];
    }
    document.getElementById("word-describe").textContent = listWord[1];
    document.getElementById("word-detail_describe").textContent = listWord[2];

    // 問題数を更新
    updateQuestionNumber(currentIndex);
}

// 次へボタンを押したときの動作
document.getElementById("next-btn").addEventListener("click", function() {
    // 次の単語に進む
    currentIndex++;
    const card = document.querySelector(".card");
    card.style.transition = 'none';
    card.classList.remove("is-flipped");
     // 次の単語に進むときにはアニメーションを一度無効化
    setTimeout(function() {
        card.style.transition = ''; // アニメーションを元に戻す
    }, 10);

    // リストの一番最後に達したとき
    if (currentIndex + 1 >= listWords.length) {
        document.getElementById("next-btn").disabled = true; // 次へボタンを無効化
    } else {
        document.getElementById("next-btn").disabled = false; // 次へボタンを有効化
    }

    // 次の単語を表示
    displayWord(currentIndex);

    // 前へボタンを有効化
    document.getElementById("prev-btn").disabled = false;
});

// 前へボタンを押したときの動作
document.getElementById("prev-btn").addEventListener("click", function() {
    // 前の単語に戻る
    currentIndex--;
    const card = document.querySelector(".card");
    card.style.transition = 'none';
    card.classList.remove("is-flipped");
     // 前の単語に戻るときにはアニメーションを一度無効化
    setTimeout(function() {
        card.style.transition = ''; // アニメーションを元に戻す
    }, 10);

    // リストの一番初めに達したとき
    if (currentIndex <= 0) {
        document.getElementById("prev-btn").disabled = true; // 前へボタンを無効化
    } else {
        document.getElementById("prev-btn").disabled = false; // 前へボタンを有効化
    }

    // 次の単語を表示
    displayWord(currentIndex);

    // 次へボタンを有効化
    document.getElementById("next-btn").disabled = false;
});

//単語を表示
displayWord(currentIndex);
document.getElementById("prev-btn").disabled = true;
