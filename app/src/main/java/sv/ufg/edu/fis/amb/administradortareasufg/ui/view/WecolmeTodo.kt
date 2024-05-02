package sv.ufg.edu.fis.amb.administradortareasufg.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import sv.ufg.edu.fis.amb.administradortareasufg.R

class WecolmeTodo : Fragment() {
    // button
    private lateinit var welcomeBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_wecolme_todo, container, false)

        // buttons init
        welcomeBtn = view.findViewById<Button>(R.id.btn_welcome)


        // actions
        welcomeButtonAction()
        return view
    }

    private fun welcomeButtonAction() {
        welcomeBtn.setOnClickListener {
            val parentFragmetManager = parentFragmentManager.beginTransaction()
            parentFragmetManager.replace(R.id.fragment_container_view, HomeFragment())
            parentFragmetManager.commit()
        }
    }

}