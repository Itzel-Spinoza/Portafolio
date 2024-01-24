using UnityEngine;
using UnityEngine.UI;
using System.Collections.Generic;

public class QuestionPanel : MonoBehaviour
{
    public Button cerrar;
    public Text scoreText;
    public Text scoreTotal;
    public GameObject questionPanel;
    public Text resultText;
    public Text questionText;
    public Button button1;
    public Button button2;
    public Button button3;

    private static int totalScore = 0;
    private bool answerSelected = false;

    private List<Question> questions = new List<Question>();
    private Question currentQuestion;

    private Dictionary<Button, string> answerMappings = new Dictionary<Button, string>();

    private void Start()
    {
        ResetScores();
        cerrar.onClick.AddListener(CerrarClicked);

        AddQuestions();

        scoreText.text = "Puntaje: " + totalScore.ToString();
        scoreTotal.text = "Puntaje Total: " + totalScore.ToString();

        AssignButtonClickListeners();

        ShowRandomQuestion();
    }

    private void AddQuestions()
    {
        questions.Add(new Question("¿Cuánto es 1/4 + 1/4?", "1/2"));
        questions.Add(new Question("¿Cuánto es 3/5 + 1/5?", "4/5"));
        questions.Add(new Question("¿Cuánto es 2/3 + 1/3?", "1"));
        questions.Add(new Question("¿Cuánto es 3/4 - 2/4?", "1/4"));
        questions.Add(new Question("¿Cuánto es 5/6 - 1/6?", "4/6"));
        questions.Add(new Question("Si tienes 2/3 de una pizza y le das 1/4 a tu amigo, ¿cuánta pizza te queda?", "5/12"));
    }

    private void ShowRandomQuestion()
    {
        resultText.text = ""; // Limpiar el texto resultText
        if (questions.Count > 0)
        {
            int randomIndex = Random.Range(0, questions.Count);
            currentQuestion = questions[randomIndex];

            questionText.text = currentQuestion.QuestionText;

            AssignRandomAnswers();

            questions.RemoveAt(randomIndex);
        }
        else
        {
            questionPanel.SetActive(false);
        }
    }

    private void AssignRandomAnswers()
    {
        List<string> allAnswers = new List<string>();
        allAnswers.Add(currentQuestion.CorrectAnswer);
        allAnswers.AddRange(GenerateIncorrectAnswers());

        List<Button> shuffledButtons = new List<Button> { button1, button2, button3 };
        shuffledButtons = ShuffleButtons(shuffledButtons);

        for (int i = 0; i < shuffledButtons.Count; i++)
        {
            shuffledButtons[i].GetComponentInChildren<Text>().text = allAnswers[i];
            answerMappings[shuffledButtons[i]] = allAnswers[i];
        }

        EnableButtons();
    }

    private List<string> GenerateIncorrectAnswers()
    {
        List<string> incorrectAnswers = new List<string>();

        incorrectAnswers.Add("2/3");
        incorrectAnswers.Add("2");
        incorrectAnswers.Add("2/5");
        incorrectAnswers.Add("1/6");
        incorrectAnswers.Add("3/2");
        incorrectAnswers.Add("2/6");
        incorrectAnswers.Add("3/4");
        incorrectAnswers.Add("1/8");

        ShuffleList(incorrectAnswers); // Mezclar las respuestas incorrectas

        return incorrectAnswers;
    }

    private void ShuffleList<T>(List<T> list)
    {
        int n = list.Count;
        while (n > 1)
        {
            n--;
            int k = Random.Range(0, n + 1);
            T value = list[k];
            list[k] = list[n];
            list[n] = value;
        }
    }

    private void AssignButtonClickListeners()
    {
        button1.onClick.AddListener(() => AnswerButtonClicked(button1));
        button2.onClick.AddListener(() => AnswerButtonClicked(button2));
        button3.onClick.AddListener(() => AnswerButtonClicked(button3));
    }

    private void AnswerButtonClicked(Button clickedButton)
    {
        if (!answerSelected)
        {
            answerSelected = true;

            if (answerMappings.ContainsKey(clickedButton))
            {
                string selectedAnswer = answerMappings[clickedButton];

                if (selectedAnswer == currentQuestion.CorrectAnswer)
                {
                    resultText.text = "Respuesta correcta seleccionada";
                    totalScore++;
                    UpdateScoreText();
                }
                else
                {
                    resultText.text = "Respuesta incorrecta";
                }
            }

            DisableButtons();
            Invoke("CloseQuestionPanel", 2f);
        }
    }

    private void CerrarClicked()
    {
        resultText.text = ""; // Limpiar el texto resultText
        questionPanel.SetActive(false);
        EnableButtons();
        answerSelected = false;
    }

    private void CloseQuestionPanel()
    {
        questionPanel.SetActive(false);
        answerSelected = false;
        ShowRandomQuestion();
        EnableButtons();
    }

    private void EnableButtons()
    {
        button1.interactable = true;
        button2.interactable = true;
        button3.interactable = true;
    }

    private void DisableButtons()
    {
        button1.interactable = false;
        button2.interactable = false;
        button3.interactable = false;
    }

    private void UpdateScoreText()
    {
        foreach (var panel in FindObjectsOfType<QuestionPanel>())
        {
            panel.scoreText.text = "Puntaje: " + totalScore.ToString();
            panel.scoreTotal.text = "Puntaje Total: " + totalScore.ToString();
        }
    }

    public void ResetScores()
    {
        totalScore = 0;
        scoreText.text = "Puntaje: 0";
        scoreTotal.text = "Puntaje Total: 0";
    }

    private List<Button> ShuffleButtons(List<Button> buttons)
    {
        List<Button> shuffledButtons = new List<Button>(buttons);

        int n = shuffledButtons.Count;
        while (n > 1)
        {
            n--;
            int k = Random.Range(0, n + 1);
            Button value = shuffledButtons[k];
            shuffledButtons[k] = shuffledButtons[n];
            shuffledButtons[n] = value;
        }

        return shuffledButtons;
    }
}

public class Question
{
    public string QuestionText { get; }
    public string CorrectAnswer { get; }

    public Question(string questionText, string correctAnswer)
    {
        QuestionText = questionText;
        CorrectAnswer = correctAnswer;
    }
}