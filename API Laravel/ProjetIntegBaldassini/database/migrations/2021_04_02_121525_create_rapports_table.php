<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateRapportsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('rapports', function (Blueprint $table) {
            $table->id();
            $table->string('nomTechnicien');
            $table->string('prenomTechnicien');
            $table->string('nomClient');
            $table->string('prenomClient');
            $table->string('adresse');
            $table->string('marqueChaudiere');
            $table->string('modeleChaudiere');
            $table->date('dateMiseService');
            $table->integer('numSerie');
            $table->date('dateIntervention');
            $table->text('description');
            $table->time('temps');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('rapports');
    }
}
