package com.stamford.pos22021

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)


        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val layout = inflater.inflate(
            R.layout.fragment_product, container, false

        )

        val rvMacarons = layout.findViewById<View>(R.id.rvProductListInFragment) as RecyclerView

        val macarons = Macaron.createMacaronsList()

        val adapter = MacaronAdapter(macarons) {
            macaron -> Log.d(TAG, "onClick_Listener: ${macaron.name}")

            val transaction = parentFragmentManager.beginTransaction()
            val showProductFrag = ShowProductFragment ()

            showProductFrag.arguments = Bundle(1).apply {
                putInt("macaron_id_int", macaron.id.toInt())
            }

            val view: FragmentContainerView? =
                activity?.findViewById<FragmentContainerView>(R.id.show_product_fragmentContainerView2)

            if (view != null) {
                transaction.replace(R.id.show_product_fragmentContainerView2, showProductFrag)
            } else {
                transaction.replace(R.id.product_list_fragmentContainerView, showProductFrag)
            }
            transaction.addToBackStack(null)
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            transaction.commit()
        }
        rvMacarons.adapter = adapter

        rvMacarons.layoutManager = LinearLayoutManager(layout.context)

        return layout

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}