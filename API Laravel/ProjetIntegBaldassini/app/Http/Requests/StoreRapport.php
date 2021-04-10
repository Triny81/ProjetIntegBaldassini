<?php

namespace App\Http\Requests;

use Illuminate\Foundation\Http\FormRequest;

class StoreRapport extends FormRequest
{
    /**
     * Determine if the user is authorized to make this request.
     *
     * @return bool
     */
    public function authorize()
    {
        return true;
    }

    /**
     * Get the validation rules that apply to the request.
     *
     * @return array
     */
    public function rules()
    {
        return [
            'nomTechnicien' => 'required|string|max:255',
            'prenomTechnicien' => 'required|string|max:255',
            'nomClient' => 'required|string|max:255',
            'prenomClient' => 'required|string|max:255',
            'adresse' => 'required|string|max:255',
            'marqueChaudiere' => 'required|string|max:255',
            'modeleChaudiere' => 'required|string|max:255',
            'dateMiseService' => 'required',
            'numSerie' => 'required|integer',
            'dateIntervention' => 'required',
            'description' => 'string',
            'temps' => 'required',
        ];
    }
}
