-- 1. Insertion de la prestation "Retouches"
INSERT INTO service (id, nom, sous_titre, description)
VALUES (
           'S1RV',
           'Retouches de vêtements',
           'Rien ne se perd, tout se transforme… même vos vêtements.',
           'Retoucher un vêtement permet de l''adapter à votre corps, à votre style et à votre confort.

       Qu''il s''agisse de raccourcir un pantalon, de reprendre une taille ou de l''agrandir, d''ajuster une veste, de redonner une coupe moderne à un vêtement oublié dans le placard, chaque retouche permet à vos habits de retrouver leur place dans votre quotidien.

       Porter un vêtement bien ajusté, dans lequel on se sent à l''aise et mis en valeur, contribue à renforcer la confiance et l''estime de soi.

       En retouchant vos vêtements, je les adapte à votre silhouette pour un confort et une allure qui vous ressemblent.'
       );

-- 2. Insertion de la prestation "Réparation"
INSERT INTO service (id, nom, sous_titre, description)
VALUES (
           'S2RV',
           'Réparation de vêtements',
           'Réparer, c''est aimer un peu plus longtemps.',
           'Un accro, une couture qui lâche, une fermeture cassée… ce sont souvent de petits détails qui nous font mettre un vêtement au fond du placard.

       Je suis là pour leur redonner vie !

       Faire réparer c''est refuser de jeter ce qui peut être encore porté, c''est faire le choix de la durabilité plutôt que de remplacer.

       En réparant vos vêtements, vous faites un acte éco responsable.

       Grace au Bonus réparation, une partie du coût de la réparation est prise en charge, rendant ce service plus accessible pour tous.'
       );

-- 3. Insertion du cours "Couture à domicile"
INSERT INTO service (id, nom, sous_titre, description)
VALUES (
           'C1RS',
           'Cours de couture à domicile',
           'Donne un poisson à quelqu’un, il mangera un jour, apprend lui à pécher il mangera toute sa vie.',
           'Et si vous appreniez à faire par vous-même ?

       Je vous accompagne pas à pas selon vos besoins :
       - Prise en main de votre machine à coudre ou de votre surjeteuse
       - Initiation à la retouche et à la réparation de vos vêtements
       - Accompagnement dans vos projets personnels (accessoires, déco, création sur mesure…)

       Je m’adapte à votre niveau en mettant à votre disposition mon savoir faire professionnel.

       En maitrisant des techniques spécifiques, les gestes professionnels, vous gagnerez en autonomie, confiance et fierté de créer de vos propres mains.'
       );