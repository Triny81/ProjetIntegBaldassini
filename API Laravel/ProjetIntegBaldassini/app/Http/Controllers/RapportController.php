<?php

namespace App\Http\Controllers;

use App\Models\Rapport;
use App\Http\Resources\RapportResource;
use App\Http\Requests\StoreRapport;

class RapportController extends Controller
{
    public function __construct()
    {
        // rate limiting
        $this->middleware('throttle:60,1');
        $this->middleware(\Fruitcake\Cors\HandleCors::class);
    }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return RapportResource::collection(Rapport::paginate());
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  StoreRapport  $request
     * @return \Illuminate\Http\Response
     */
    public function store(StoreRapport $request)
    {
        $validated = $request->validated();
        Rapport::create($validated);
    }

    /**
     * Display the specified resource.
     *
     * @param  \App\Models\Rapport  $rapport
     * @return \Illuminate\Http\Response
     */
    public function show(Rapport $rapport)
    {
        return new RapportResource($rapport);
    }
}
