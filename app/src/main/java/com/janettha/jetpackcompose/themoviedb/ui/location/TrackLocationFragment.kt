package com.janettha.jetpackcompose.themoviedb.ui.location

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.janettha.jetpackcompose.themoviedb.R
import com.janettha.jetpackcompose.themoviedb.core.util.extensions.failureFirebase
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request.LocationFirebase
import com.janettha.jetpackcompose.themoviedb.data.datasource.web.model.FailureFirebaseResponse
import com.janettha.jetpackcompose.themoviedb.databinding.FragmentTrackLocationBinding
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.TrackLocationService.Constants.ACTION_START_OR_RESUME_SERVICE
import com.janettha.jetpackcompose.themoviedb.sys.config.Constants.TrackLocationService.Constants.ACTION_STOP_SERVICE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackLocationFragment : Fragment(), OnMapReadyCallback {
    private val mTag = "TrackLocationFragment"

    // region COMPONENTS
    private lateinit var binding: FragmentTrackLocationBinding

    private val viewModel by viewModels<TrackLocationViewModel>()
    // endregion

    // region VARIABLES
    private lateinit var locationReceiver: LocationReceiver

    var db = FirebaseFirestore.getInstance()
    private lateinit var googleMap: GoogleMap
    // endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            failureFirebase(failure, ::handleFailure)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrackLocationBinding.inflate(layoutInflater, container, false)

        locationReceiver = LocationReceiver()

        val isServiceRunning = TrackingLocationService.isServiceRunning

        binding.materialButtonTrackLocation.text = if (isServiceRunning) {
            getString(R.string.real_time_location_pause_finished)
        } else {
            getString(R.string.real_time_location_start)
        }

        setUpMapView(savedInstanceState)

        binding.materialButtonTrackLocation.setOnClickListener {
            if (isServiceRunning) {
                sendServiceCommand(ACTION_START_OR_RESUME_SERVICE)
            } else {
                sendServiceCommand(ACTION_STOP_SERVICE)
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.mapViewLocationFirebase.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            locationReceiver,
            IntentFilter("intent_broadcast_location")
        )
    }

    override fun onPause() {
        super.onPause()
        binding.mapViewLocationFirebase.onPause()
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(locationReceiver)
    }

    private fun sendServiceCommand(action: String) {
        Intent(requireContext(), TrackingLocationService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }
    }

    private fun setUpMapView(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        savedInstanceState?.let {
            mapViewBundle = savedInstanceState.getBundle("map_view_bundle_key")
        }
        binding.mapViewLocationFirebase.onCreate(mapViewBundle)
        binding.mapViewLocationFirebase.getMapAsync(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle("map_view_bundle_key")
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle("map_view_bundle_key", mapViewBundle)
        }
        binding.mapViewLocationFirebase.onSaveInstanceState(mapViewBundle)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap?) {
        map?.isMyLocationEnabled = true
        map?.let {
            googleMap = it
        }
        setCameraView()
    }

    private fun setCameraView() {
        db.collection("location")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.d(mTag, "setCameraView: Listen failed.")
                    return@addSnapshotListener
                }
                for (doc in value!!) {
                    val post = doc.toObject(LocationFirebase::class.java)
                    val uno = LatLng(post.latitude.toDouble(), post.longitude.toDouble())
                    googleMap.addMarker(MarkerOptions().position(uno).title(post.currentdate))
                    googleMap.animateCamera(CameraUpdateFactory.newLatLng(uno))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uno, 11f))
                }
            }
    }

    private fun handleFailure(failure: FailureFirebaseResponse?) {
        when (failure) {
            is FailureFirebaseResponse.NetworkConnection -> renderFailure(R.string.no_network_connection)
            is FailureFirebaseResponse.ServerError -> renderFailure(R.string.no_network_connection)
            else -> renderFailure(R.string.error_server)
        }
    }

    private fun renderFailure(@StringRes message: Int) {
        notify(message)
    }

    private fun notify(@StringRes message: Int) =
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()

    private inner class LocationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            Log.d(mTag, "onReceive: ${intent.action}")
        }
    }

    // region map
    override fun onStart() {
        super.onStart()
        binding.mapViewLocationFirebase.onStart()
    }

    override fun onStop() {
        super.onStop()
        binding.mapViewLocationFirebase.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapViewLocationFirebase.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapViewLocationFirebase.onLowMemory()
    }
    // endregion
}