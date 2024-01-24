using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FruitCollected : MonoBehaviour
{
    //es para que desaparesca la fruta cuando el jugador camine asia ella 
    public GameObject questionPanel;
    private void OnTriggerEnter2D(Collider2D collision)
    {
        if(collision.CompareTag("Player"))
        {
            GetComponent<SpriteRenderer>().enabled = false; 
            gameObject.transform.GetChild(0).gameObject.SetActive(true);
           
           
           // Mostrar el panel de preguntas
            questionPanel.SetActive(true);
            Destroy(gameObject,0.5f);
        }
    }
}

