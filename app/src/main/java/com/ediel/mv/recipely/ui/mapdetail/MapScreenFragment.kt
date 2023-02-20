package com.ediel.mv.recipely.ui.mapdetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ediel.mv.recipely.R
import com.ediel.mv.recipely.databinding.HomeScreenFragmentBinding
import com.ediel.mv.recipely.databinding.MapScreenFragmentBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "latitude"
private const val ARG_PARAM2 = "longitude"
private const val ARG_PARAM3 = "name"

/**
 * A simple [Fragment] subclass.
 * Use the [MapScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapScreenFragment : Fragment(), OnMapReadyCallback {
    // TODO: Rename and change types of parameters
    private var latitude: String? = null
    private var longitude: String? = null
    private var name: String? = null

    private var mMap: GoogleMap? = null
    private var _binding: MapScreenFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            latitude = it.getString(ARG_PARAM1)
            longitude = it.getString(ARG_PARAM2)
            name = it.getString(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.map_screen_fragment, container, false)
        _binding = MapScreenFragmentBinding.inflate(inflater, container, false)
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topBar.topBarTitle.text = name.toString()
        binding.topBar.topBarLeftIcon.setImageDrawable(resources.getDrawable(R.drawable.baseline_arrow_back_24, null))
        binding.topBar.topBarLeftIcon.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MapScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String, param3: String) =
            MapScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                }
            }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        Log.v("MapScreenFragment", "Ready")
        mMap = googleMap
        val current = LatLng(latitude?.toDouble() ?: 19.432794, longitude?.toDouble() ?: -99.133162)
        val location = mMap?.addMarker(MarkerOptions().position(current).title("Lugar de pertenencia de la receta"))
        location?.showInfoWindow()
        mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(current, 15F))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}