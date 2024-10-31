import { useEffect } from 'react';
import style from './About.module.css'

function About() {

    useEffect(() => {
        document.body.style.backgroundColor = '#A1E158';
      }, []);

    return (
        <div className={style.about_page}>
            <h1>Bienvenue dans Eco Challenge 🌍🎮</h1>

            <h2>Préparez-vous à plonger dans un univers passionnant où l'amusement et la sensibilisation à l'écologie se rencontrent !</h2>

            <p>Dans Eco Challenge, vous êtes le héros d'une aventure interactive qui met à l'épreuve vos compétences en matière de durabilité et de
            respect de l'environnement. Explorez votre ville, ou morceau de ville à plusieurs en mode multijoueur, réparez des infrastrucures et
            relevez des défis écologiques qui feront de vous un champion de l'écologie !</p>

            <p>🌱 Ce qui vous attend :</p>

            <ul>
                <li>Des quêtes inspirantes : Participez à des missions qui vous sensibiliseront aux enjeux environnementaux tout en vous amusant</li>
                <li>Des choix impactants : Vos décisions influencent l'environnement du jeu et l'écosystème qui vous entoure. Chaque action compte !</li>
                <li>Des graphismes enchanteurs : Plongez dans un monde coloré où chaque élément est conçu pour éveiller votre conscience écologique</li>
                <li>Des leçons sur la durabilité : Apprenez des faits fascinants sur l'environnement et découvrez comment vous pouvez faire la différence,
                    dans le jeu et dans la vie réelle.</li>
            </ul>

            <p>Prêt à relever le défi ? Armez-vous de connaissances et préparez-vous à sauver notre planète !</p>
        </div>
    )
}

export default About