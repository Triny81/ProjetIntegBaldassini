<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Rapport extends Model
{
    use HasFactory;

    protected $fillable = [
        'nomTechnicien',
        'prenomTechnicien',
        'nomClient',
        'prenomClient',
        'adresse',
        'marqueChaudiere',
        'modeleChaudiere',
        'dateMiseService',
        'numSerie',
        'dateIntervention',
        'description',
        'temps',
    ];
}
